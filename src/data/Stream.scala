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

  def exists(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean) = !exists(!p(_))

  def takeWhile2(p: A => Boolean): Stream[A] = foldRight(Stream.empty[A])((a, as) => if (p(a)) Stream.cons(a, as) else Stream.empty)

  def map[B](f: A => B): Stream[B] = foldRight(Stream.empty[B])((a, bs) => Stream.cons(f(a), bs))

  def filter(p: A => Boolean): Stream[A] = foldRight(Stream.empty[A])((a, as) => if (p(a)) Stream.cons(a, as) else as)

  def flatMap[B](f: A => Stream[B]): Stream[B] = Stream.flatten(this.map(f))
}

object Stream {
  def append[A](as1: Stream[A], as2: => Stream[A]): Stream[A] = as1.foldRight(as2)((a, as) => Stream.cons(a, as))

  def flatten[A](ass: Stream[Stream[A]]): Stream[A] = ass.foldRight(Stream.empty[A])(append[A])

  def empty[A]: Stream[A] = new Stream[A] { def uncons = none }

  def cons[A](head: => A, tail: => Stream[A]): Stream[A] =
    new Stream[A] {
      lazy val uncons = some((head, tail))
    }

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = cons(1, ones)

  def constant[A](a: A): Stream[A] = cons(a, constant(a))

  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  def fibGen(a: Int, b: Int): Stream[Int] = cons(a + b, fibGen(b, a + b))
  def fib: Stream[Int] = cons(0, cons(1, fibGen(0, 1)))

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case some((a, z2)) => cons(a, unfold(z2)(f))
    case data.none => Stream.empty
  }

  def ones2: Stream[Int] = unfold(1)((_: Int) => some((1, 1)))

  def constant2[A](a : A) : Stream[A] = unfold(())((_ : Unit) => some((a,())))
  
  def from2(n: Int): Stream[Int] = unfold(n)((a : Int) => some((a,a+1)))
  
  def fib2 : Stream[Int] = unfold((0,1))( (ab : (Int,Int)) => { 
    val a = ab._1; 
    val b = ab._2; 
    some(a,(b,a+b))
  })
  
}