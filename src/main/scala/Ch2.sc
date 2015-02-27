object Ch2 {

  import Chapter2Exercises._

  (0 to 10).map(Fib).toString
  isSorted(Array(1, 2, 3), (a: Int, b: Int) => a < b)

  isSorted(Array(3, 2, 4), (a: Int, b: Int) => a < b)

  val sum: (Int, Int) => Int = (a, b) => a + b

  val inc1 = partial1(1, sum)
  inc1(1)
  uncurry(curry(sum))(1, 1)
}