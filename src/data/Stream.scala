package data

trait Stream[+A] {
  def uncons: Option[(A, Stream[A])]

  def isEmpty: Boolean = uncons.isEmpty

  def toList: List[A] = uncons match {
    case data.none => Nil
    case some((a, as)) => Cons(a, as.toList)
  }

  def take(n: Int): Stream[A] =
    if (n == 0) Stream.empty
    else uncons match {
      case some((a, as)) => Stream.cons(a, as.take(n - 1))
      case data.none => Stream.empty
    }

  def takeWhile(p: A => Boolean): Stream[A] = uncons match {
    case some((a, as)) =>
      if (p(a)) Stream.cons(a, as.takeWhile(p))
      else Stream.empty
    case data.none => Stream.empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B =
    uncons match {
      case some((h, t)) => f(h, t.foldRight(z)(f))
      case data.none => z
    }
  
  def exists(p: A => Boolean) : Boolean = foldRight(false)((a,b) => p(a) || b)
  
  def forAll(p: A => Boolean) = !exists(!p(_))
  
  def takeWhile2(p: A => Boolean) : Stream[A] = foldRight(Stream.empty[A])((a,as) => if (p(a)) Stream.cons(a,as) else Stream.empty )
  
  def map[B](f: A => B) : Stream[B] = foldRight(Stream.empty[B])((a,bs) => Stream.cons(f(a),bs))
  
}

object Stream {
  def empty[A]: Stream[A] = new Stream[A] { def uncons = none }

  def cons[A](head: => A, tail: => Stream[A]): Stream[A] =
    new Stream[A] {
      lazy val uncons = some((head, tail))
    }

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))
    
  val ones: Stream[Int] = cons(1, ones)
}