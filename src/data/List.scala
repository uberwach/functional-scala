package data

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

    def foldRight[A,B](l : List[A], z: B)(f: (A,B) => B) : B = {
      l match {
        case Nil => z
        case Cons(x,xs) => f(x, foldRight(xs, z)(f))
      }
    }
    def foldLeft[A,B](as : List[A], b: B)(f: (B,A) => B) : B = {
      @annotation.tailrec
      def foldLeftAcc(as : List[A], acc : B) : B = {
        foldLeftAcc(tail(as),f(acc,head(as)))
      }
      foldLeftAcc(as,b)
    }
    
  // test function to create big lists
  // gen(1),...,gen(length) is created
  def genSequence[A](length : Int, gen: Int => A) : List[A] = {
    @annotation.tailrec
    def genAcc(as : List[A], n: Int) : List[A] = {
      if (n > length) as
      else genAcc(Cons(gen(n),as),n+1)
    }
    genAcc(Nil,1)
  }
  // Pattern match version
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }
  // Fold version
  def sum2(ints: List[Int]) : Int = foldRight(ints,0)(_ + _)
  def product2(ds: List[Double]) : Double = foldRight(ds,1.0)(_ * _)

  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }
  
  def head[A](as : List[A]): A = as match {
    case Nil => throw sys.error("No head of an empty list!")
    case Cons(x,xs) => x
  }

  def drop[A](as: List[A], n: Int): List[A] = {
    @annotation.tailrec
    def dropacc(as: List[A], n: Int, acc: List[A]): List[A] = {
      if (n == 0) acc
      else as match {
        case Nil => acc
        case Cons(x, xs) => dropacc(xs, n - 1, Cons(x, acc))
      }
    }
    dropacc(as, n, Nil)
  }

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = {
    @annotation.tailrec
    def dropacc(as: List[A], acc: List[A]): List[A] = {
      as match {
        case Nil => acc
        case Cons(x, xs) => if (!f(x)) acc else dropacc(xs, Cons(x, acc))
      }
    }
    dropacc(as, Nil)
  }
  def init[A](as: List[A]): A = {
    @annotation.tailrec
    def innerinit(as: List[A], a: A): A = {
      as match {
        case Nil => a
        case Cons(x, xs) => innerinit(tail(as), x)
      }
    }
    as match {
      case Nil => throw sys.error("nope")
      case Cons(x, xs) => innerinit(xs, x)
    }
  }
  def setHead[A](as: List[A], a: A) = Cons(a, tail(as))
  
  def length[A](as : List[A]) : Int = foldRight(as,0)( (a,b) => b + 1)
}