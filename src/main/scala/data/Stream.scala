package data

trait Stream[+A] {
  def uncons: Option[(A, Stream[A])]

  def isEmpty: Boolean = uncons.isEmpty

  def head: A = uncons match {
    case data.none => sys.error("No head of empty stream!")
    case some((a, as)) => a
  }

  def tail: Stream[A] = uncons match {
    case data.none => Stream.empty
    case some((a, as)) => as
  }

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

  // implementations using unfold

  import Stream.unfold

  def map2[B](f: A => B): Stream[B] = unfold(this)(s => {
    if (s.isEmpty) data.none
    else some((f(s.head), s.tail))
  })

  def take2(n: Int): Stream[A] = unfold((this, n))(snum => {
    val s = snum._1
    val num = snum._2
    if (this.isEmpty || num == 0) data.none
    else some((s.head, (s.tail, num - 1)))
  })

  def takeWhile3(p: A => Boolean): Stream[A] = unfold(this)(s => {
    if (!s.isEmpty && p(s.head)) some((s.head, s.tail))
    else data.none
  })

  def zip[B](bs: Stream[B]): Stream[(A, B)] = unfold[(A, B), (Stream[A], Stream[B])]((this, bs))(sAsB => {
    val sa = sAsB._1
    val sb = sAsB._2
    if (sa.isEmpty || sb.isEmpty) data.none
    else some(((sa.head, sb.head), (sa.tail, sb.tail)))
  })

  def tails: Stream[Stream[A]] = Stream.append(unfold(this)(s => {
    if (s.isEmpty) data.none
    else some((s, s.tail))
  }), Stream(Stream.empty))

  // a O(n²) implementation would be tails.map(fold)
  def scanRight[B](b: B)(f: (A, => B) => B): Stream[B] = tails.map(s => s.foldRight(b)(f))

  def scanLeft[B](b: B)(f: (A, => B) => B): Stream[B] = unfold((this, b, false))(sb => {
    val s = sb._1;
    // the stream of As
    val binter = sb._2 // intermediate result of accumulator
    val cancellation = sb._3
    if (cancellation) data.none
    else if (s.isEmpty) some((binter), (s, binter, true))
    else some((binter, (s.tail, f(s.head, binter), false)))
  })
}

object Stream {
  def append[A](as1: Stream[A], as2: => Stream[A]): Stream[A] = as1.foldRight(as2)((a, as) => Stream.cons(a, as))

  def flatten[A](ass: Stream[Stream[A]]): Stream[A] = ass.foldRight(Stream.empty[A])(append[A])

  def empty[A]: Stream[A] = new Stream[A] {
    def uncons = none
  }

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] =
    new Stream[A] {
      lazy val uncons = some((hd, tl))
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

  def constant2[A](a: A): Stream[A] = unfold(())((_: Unit) => some((a, ())))

  def from2(n: Int): Stream[Int] = unfold(n)((a: Int) => some((a, a + 1)))

  def fib2: Stream[Int] = unfold((0, 1))((ab: (Int, Int)) => {
    val a = ab._1;
    val b = ab._2;
    some(a, (b, a + b))
  })

  // zipAll semantics are different to the standard library it seems
  def zipAll[A, B](as: Stream[A], bs: Stream[B]): Stream[(Option[A], Option[B])] = unfold((as, bs))(sAsB => {
    val sa = sAsB._1
    val sb = sAsB._2
    if (sa.isEmpty && sb.isEmpty) data.none
    else some(((if (sa.isEmpty) data.none else some(sa.head), if (sb.isEmpty) data.none else some(sb.head)), (sa.tail, sb.tail)))
  })

  def startsWith[A](s1: Stream[A], s2: Stream[A]): Boolean = if (s2.isEmpty) true
  else if (s1.isEmpty) false
  else {
    // both streams are non-empty
    val combinedStream = Stream.zipAll(s1, s2)
    combinedStream.forAll(pairPair => {
      val a1 = pairPair._1
      val a2 = pairPair._2
      (a1 == a2) || (a2 == data.none)
    })
  }

  def hasSubsequence[A](s1: Stream[A], s2: Stream[A]): Boolean =
    s1.tails exists (startsWith(_, s2))
}