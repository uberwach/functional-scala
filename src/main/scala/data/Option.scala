package data

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case some(a) => some(f(a))
    case none => data.none
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this map f getOrElse none

  def getOrElse[B >: A](default: => B): B = this match {
    case some(a) => a
    case none => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this.flatMap[Option[A]]((a: A) => some(some(a))).getOrElse(ob)

  def filter(f: A => Boolean): Option[A] = this.flatMap(a => if (f(a)) some(a) else none)


  def isEmpty: Boolean = this match {
    case data.none => true
    case _ => false
  }
}

case class some[+A](get: A) extends Option[A]

case object none extends Option[Nothing]

// Chapter 4
object Option {
  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def lift2[A,B,C](f: (A,B) => C): (Option[A], Option[B]) => Option[C] = map2(_,_)(f)

  def map2[A, B, C](aOpt: Option[A], bOpt: Option[B])(f: (A, B) => C): Option[C] =
    for {
      a <- aOpt
      b <- bOpt
    } yield f(a, b)

  def sequence[A](opts: List[Option[A]]): Option[List[A]] =
    if (opts.exists(_ == none)) none
    else some(opts.flatMap((a: Option[A]) => a match {
      case some(x) => List(x)
      case none => Nil
    }))

  // this is a foldRight in disguise I think, needs some way to stop when it is clear that result will be none
  def traverse[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] = {
    def traverseAcc(rest: List[A]): Option[List[B]] = {
      rest match {
        case Cons(x, xs) => {
          val y = f(x)
          y match {
            case some(b) => {
              val optList = traverseAcc(List.tail(rest))
              optList match {
                case some(list) => some(Cons(b, list))
                case none => data.none
              }
            }
            case none => data.none
          }
        }
        case Nil => some(Nil)
      }
    }
    traverseAcc(as)
  }

  def sequence2[A](opts: List[Option[A]]): Option[List[A]] = traverse(opts)(x => x)

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) none
    else some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = {
    val mOpt = mean(xs)
    mOpt.flatMap { m =>
      mean(xs.map(x => math.pow(x - m, 2)))
    }
  }

  import java.util.regex._

  def pattern(s: String): Option[Pattern] =
    try {
      some(Pattern.compile(s))
    } catch {
      case e: PatternSyntaxException => none
    }

  def mkMatcher(pat: String): Option[String => Boolean] =
    pattern(pat) map (p => (s: String) => p.matcher(s).matches)

  def bothMatch_2(pat1: String, pat2: String, s: String): Option[Boolean] = map2(mkMatcher(pat1), mkMatcher(pat2))((a, b) => a(s) && b(s))


  // inefficient
  def find[A](as: List[A])(p: A => Boolean): Option[A] = {
    val results = as.flatMap(a => if (p(a)) List(a) else Nil)
    if (List.isEmpty(results)) none
    else some(results.head)
  }

}