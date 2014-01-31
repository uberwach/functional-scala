package data

sealed trait List[+A] {
  def exists(p: A => Boolean) : Boolean = !List.forall(this)(!p(_))
  def map[B](f: A => B) : List[B] = List.map(this)(f)
  def flatMap[B](f: A => List[B]) : List[B] = List.flatMap(this)(f)
  def head : A = List.head(this)
}
case object Nil extends List[Nothing]
case class Cons[+A](heada: A, tail: List[A]) extends List[A]

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

  // NOT AN EXERCISE
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
  
  def isEmpty[A](as : List[A]) : Boolean = as match {
    case Nil => true
    case _ => false
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
  // for example rewriting sum:
  def sum3(as: List[Int]): Int = foldLeft(as, 0)(_ + _)

  def reverse[A](as: List[A]): List[A] = foldLeft(as, Nil: List[A])((b, a) => Cons(a, b))
  
  def foldLeft2[A,B](as : List[A], b: B)(f: (B,A) => B) = foldRight(reverse(as),b)( (a,b) => f(b,a))
  def foldRight2[A,B](as : List[A], b: B)(f: (A,B) => B) = foldLeft(reverse(as),b)( (b,a) => f(a,b))
  
  def append2[A](as1 : List[A], as2 : List[A]) : List[A] = foldRight(as1, as2)( (a,as) => Cons(a,as) )
  
  // that identifier though
  def flatten[A](ass : List[List[A]]) : List[A] = foldLeft(ass, Nil : List[A])(append)
  
  def map[A,B](as : List[A])(f: A => B) : List[B] = foldRight(as, Nil : List[B])((a,bs) => Cons(f(a),bs))
  
  def addOne(ns : List[Int]) = map(ns)( (x : Int) => x+1)
  def print(ds : List[Double]) : List[String] = map(ds)( (_ : Double).toString)
  
  def filter[A](as: List[A], f: A => Boolean) : List[A] = foldRight(as, Nil: List[A])((a,xs) => {
    if (f(a)) Cons(a,xs)
    else xs
  })
  
  def flatMap[A,B](as: List[A])(f: A => List[B]) : List[B] = flatten(map(as)(f))
  // filter by using flatMap
  def filter2[A](as: List[A])(f: A => Boolean) = flatMap(as)(a => if (f(a)) List(a) else Nil)
  
  // definitely not optimal
  def zip[A,B](as : List[A], bs : List[B]) : List[(A,B)] = {
    @annotation.tailrec
    def zipAcc(xs: List[A], ys : List[B], zs : List[(A,B)]) : List[(A,B)] = {
      if (isEmpty(xs) || isEmpty(ys)) zs
      else zipAcc(tail(xs),tail(ys), Cons((head(xs),head(ys)),zs))
    }
    reverse(zipAcc(as,bs,Nil))
  }
  
  def combine[A,B,C](as : List[A], bs : List[B])( f: (A,B) => C) : List[C] = map(zip(as,bs))( ab => f(ab._1,ab._2))

  def forall[A](as: List[A])(p: A => Boolean): Boolean = foldLeft(as, true)((b, a) => b && p(a))
  // inefficient but natural
  def hasSubsequence[A](as: List[A], sub: List[A]): Boolean = {
    if (isEmpty(sub)) true
    else if (isEmpty(as)) false
    else {
      val asub = zip(as,sub)
      (forall(asub)(x => x._1 == x._2) && (length(asub) == length(sub))) || hasSubsequence(tail(as),sub)
    }
  }
}