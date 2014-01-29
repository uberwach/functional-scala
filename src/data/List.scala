package data

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
    l match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }
  def foldLeft[A, B](as: List[A], b: B)(f: (B, A) => B): B = {
    @annotation.tailrec
    def foldLeftAcc(as: List[A], acc: B): B = {
      as match {
        case Nil => acc
        case Cons(x, xs) => foldLeftAcc(xs, f(acc, x))
      }
    }
    foldLeftAcc(as, b)
  }

  // test function to create big lists
  // gen(1),...,gen(length) is created
  def genSequence[A](length: Int, gen: Int => A): List[A] = {
    @annotation.tailrec
    def genAcc(as: List[A], n: Int): List[A] = {
      if (n > length) as
      else genAcc(Cons(gen(n), as), n + 1)
    }
    genAcc(Nil, 1)
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
  def sum2(ints: List[Int]): Int = foldRight(ints, 0)(_ + _)
  def product2(ds: List[Double]): Double = foldRight(ds, 1.0)(_ * _)

  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }

  def head[A](as: List[A]): A = as match {
    case Nil => throw sys.error("No head of an empty list!")
    case Cons(x, xs) => x
  }

  def last[A](as: List[A]): A = {
    @annotation.tailrec
    def lastAcc(l: List[A], a: A): A = {
      l match {
        case Nil => a
        case Cons(x, xs) => lastAcc(xs, x)
      }
    }
    as match {
      case Nil => sys.error("No last element of []")
      case Cons(x, xs) => lastAcc(xs, x)
    }
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

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def length[A](as: List[A]): Int = foldRight(as, 0)((a, b) => b + 1)

  // foldLeft versions are just foldRight replaced with foldLeft and use g(a,b) = f(b,a) instead of f
  def sum3(as: List[Int]): Int = foldLeft(as, 0)(_ + _)

  def reverse[A](as: List[A]): List[A] = foldLeft(as, Nil: List[A])((b, a) => Cons(a, b))
  
  def foldLeft2[A,B](as : List[A], b: B)(f: (B,A) => B) = foldRight(reverse(as),b)( (a,b) => f(b,a))
  def foldRight2[A,B](as : List[A], b: B)(f: (A,B) => B) = foldLeft(reverse(as),b)( (b,a) => f(a,b))
  
  def append2[A](as1 : List[A], as2 : List[A]) : List[A] = foldRight(as1, as2)( (a,as) => Cons(a,as) )
  
  // that identifier though
  def flatten[A](ass : List[List[A]]) : List[A] = foldLeft(ass, Nil : List[A])(append)
  
  def map[A,B](as : List[A], f: A => B) : List[B] = foldRight(as, Nil : List[B])((a,bs) => Cons(f(a),bs))
  
  def addOne(ns : List[Int]) = map(ns, (x : Int) => x+1)
  def print(ds : List[Double]) : List[String] = map(ds, (_ : Double).toString)
}