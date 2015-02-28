import par.Par
import par.Par.Par

def time[R](run: => R) : R = {
  val curr = System.currentTimeMillis()
  val result = run
  println(s"Run-time: ${System.currentTimeMillis() - curr} ms")
  result
}
def sum[T : Numeric](nums: IndexedSeq[T]): T = {
  val numeric = implicitly[Numeric[T]]
  if (nums.size <= 1)
    nums.headOption getOrElse numeric.zero
  else {
    val (l, r) = nums.splitAt(nums.length / 2)
    val sumL: Par[T] = Par.unit(sum(l))
    val sumR: Par[T] = Par.unit(sum(r))
    Par.get(Par.map2(sumL, sumR)(numeric.plus))
  }
}

// yep, parallelization costs
time {
  sum(1L to 1000000L)
}

time {
  (1L to 1000000L) sum
}