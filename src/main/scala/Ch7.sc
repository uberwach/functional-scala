import data.{some, none}
import data.Option
import data.Option._
import par.Par
import par.Par.Par

import scala.util.Random

def time[R](run: => R) : R = {
  val curr = System.currentTimeMillis()
  val result = run
  println(s"Run-time: ${System.currentTimeMillis() - curr} ms")
  result
}
def combine[T](combiner: (T,T) => T, empty: T)(as : IndexedSeq[T]) : T = {
  if (as.size <= 1)
    as.headOption getOrElse empty
  else {
    val (l, r) = as.splitAt(as.length / 2)
    val sumL: Par[T] = Par.unit(combine(combiner, empty)(l))
    val sumR: Par[T] = Par.unit(combine(combiner, empty)(r))
    Par.get(Par.map2(sumL, sumR)(combiner))
  }
}
def sum[T : Numeric](nums: IndexedSeq[T]): T = {
  val numeric = implicitly[Numeric[T]]
  combine(numeric.plus, numeric.zero)(nums)
}
def max[T: Ordering](as: IndexedSeq[T]): data.Option[T] = {
  val ordering = implicitly[Ordering[T]]
  combine[data.Option[T]](data.Option.lift2(ordering.max), none)(as.map(some(_)))
}
// parallel sampling of 10000 from a gaussian distribution and choosing the max.
val rnd = new Random()
max((1 to 10000).map(_ => rnd.nextGaussian()))
// yep, parallelization overhead strikes really hard here...
time {
  sum(1L to 1000000L)
}
time {
  (1L to 1000000L) sum
}