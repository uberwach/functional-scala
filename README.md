My solutions + playground for the exercises of the book "Functional Programming in Scala" by Runár (first through version 10 of the book, since chapter 7 through the final edition).

## Summary of cool stuff
Forcing tailrecursion by compiler
```scala
 @annotation.tailrec
    def FibAcc(n : Int, a : Int, b: Int) : Int = 
```

Reversing a List by using a fold 
```scala
def reverse[A](as : List[A]) : List[A] = foldLeft(as,Nil : List[A])((b,a) => Cons(a,b))
```

Generating streams with unfold. Think of a state machine which outputs values of the streams. Once the machine halts, the stream ends.
```scala
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case some((a, z2)) => cons(a, unfold(z2)(f))
    case data.none => Stream.empty
  }
```

The method unfold allows to give simple implementations of some methods, i.e. zipAll, zip, and tails (a stream of streams that are tails of a fixed stream).
```scala
   // in trait Stream[A]
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
  
  // on companion object Stream
  def zipAll[A, B](as: Stream[A], bs: Stream[B]): Stream[(Option[A], Option[B])] = unfold((as, bs))(sAsB => {
    val sa = sAsB._1
    val sb = sAsB._2
    if (sa.isEmpty && sb.isEmpty) data.none
    else some(((if (sa.isEmpty) data.none else some(sa.head), if (sb.isEmpty) data.none else some(sb.head)), (sa.tail, sb.tail)))
  })
```
