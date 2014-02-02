object Chapter2Exercises {

  def Fib(n: Int): Int = {
    @annotation.tailrec
    def FibAcc(n: Int, a: Int, b: Int): Int =
      if (n == 0) a
      else FibAcc(n - 1, b, a + b)
    FibAcc(n, 0, 1)
  }

  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    if (as.length <= 1) true
    else gt(as(0), as(1)) && isSorted(as.tail, gt)
  }

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = f(a, _)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = a => (b => f(a, b))
  def uncurry[A, B, C](f: A => (B => C)): (A, B) => C = (a, b) => f(a)(b)

  def compose[A, B, C](g: B => C, f: A => B): A => C = a => g(f(a))

}