package data

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }
  
  def drop[A](as: List[A], n : Int) : List[A] = {
    def dropacc(as: List[A], n : Int, acc: List[A]) : List[A] = {
      if (n == 0) acc
      else as match {
        case Nil => acc
        case Cons(x,xs) => dropacc(xs,n-1, Cons(x,acc))
      }
    }
    dropacc(as,n,Nil)
  }
  
  def dropWhile[A](as : List[A])(f : A => Boolean) : List[A] = {
    def dropacc(as: List[A], acc: List[A]) : List[A] = {
      as match {
        case Nil => acc
        case Cons(x,xs) => if (!f(x)) acc else dropacc(xs,Cons(x,acc))
      }
    }
    dropacc(as,Nil)
  }
  def init[A](as: List[A]) : A = {
    def innerinit(as: List[A],a : A) : A = {
      as match {
        case Nil => a
        case Cons(x,xs) => innerinit(tail(as),x)
      }
    }
    as match {
      case Nil => throw error("nope")
      case Cons(x,xs) => innerinit(xs,x)
    }
  }
  def setHead[A](as : List[A], a: A) = Cons(a,tail(as))
}