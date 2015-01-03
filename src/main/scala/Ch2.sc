object Ch2 {
  import Chapter2Exercises._
  (0 to 10).map(Fib).toString                     //> res0: String = Vector(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
  isSorted(Array(1, 2, 3), (a: Int, b: Int) => a < b)
                                                  //> res1: Boolean = true
  isSorted(Array(3, 2, 4), (a: Int, b: Int) => a < b)
                                                  //> res2: Boolean = false
  val sum: (Int, Int) => Int = (a, b) => a + b    //> sum  : (Int, Int) => Int = <function2>
  val inc1 = partial1(1, sum)                     //> inc1  : Int => Int = <function1>
  inc1(1)                                         //> res3: Int = 2
  uncurry(curry(sum))(1, 1)                       //> res4: Int = 2
}