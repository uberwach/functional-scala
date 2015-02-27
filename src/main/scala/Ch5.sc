object Ch5 {
  def if2[A](cond: Boolean)(onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse


  if2(false)(sys.error("fail"), 3)

  import data.Stream._
  import data._

  val test = Stream(1, 2, 3)
  test.toList

  test.take(2).toList

  test.takeWhile(_ < 2).toList

  test.exists(_ > 2)
  test.forAll(_ <= 3)
  test.forAll(_ != 2)

  test.takeWhile2(_ < 4).toList
  ones.map(_ * 2).take(5).toList

  ones.take(30).filter(_ < 10).toList

  Stream(1, 2, 3, 4, 5).filter(_ % 2 == 1).toList

  def double[A](a: A): Stream[A] = Stream(a, a)

  Stream(1, 2, 3).flatMap(double).toList
  ones.takeWhile(_ == 1)
  ones.forAll(_ != 1)

  fib.take(10).toList

  ones2.take(5).toList
  constant2("a").take(5).toList
  fib2.take(5).toList

  fib2.map2 {
    _ * 2
  }.take(5).toList
  fib2.take2(5).toList
  fib2.takeWhile3(_ < 20).toList

  fib2.zip(ones).take(10).toList


  Stream.zipAll(Stream(1, 2, 3, 4), Stream("a", "b", "c")).toList




  startsWith(Stream(1, 2, 3, 4), Stream(1, 2, 3))
  startsWith(Stream(1, 2, 3), Stream(1, 2, 3, 4))
  startsWith(Stream(1, 2), Stream(2))
  Stream(1, 2, 3).tails.map(_.toList).toList

  hasSubsequence(fib2.take(10), Stream(1, 2, 3, 5))
  hasSubsequence(Stream(1, 2, 3), Stream(2, 3))
  hasSubsequence(Stream(1, 2, 3, 4), Stream(1, 3))

  Stream(1, 2, 3).scanLeft(0)(_ + _).toList
  Stream(1, 2, 3).scanRight(0)(_ + _).toList
}