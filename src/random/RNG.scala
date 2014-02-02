package random

trait RNG {
  import RNG._
  def nextInt: (Int, RNG)
  val int: Rand[Int] = _.nextInt

}

object RNG {
  type Rand[+A] = RNG => (A, RNG)

  def simple(seed: Long): RNG = new RNG {
    def nextInt = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) &
        ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int],
        simple(seed2))
    }
  }

  def positiveInt(rng: RNG): (Int, RNG) = {
    val (n, rng2) = rng.nextInt
    if (n == Int.MinValue) positiveInt(rng2)
    else (n.abs, rng2)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (n, rng2) = positiveInt(rng)
    if (n == Int.MaxValue) double(rng2)
    else (n.toDouble / Int.MaxValue.toDouble, rng2)
  }
  // so tedious...
  def randomPair(rng: RNG): ((Int, Int), RNG) = {
    val (i1, rng2) = rng.nextInt
    val (i2, rng3) = rng2.nextInt
    ((i1, i2), rng3)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (n, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((n, d), rng3)
  }
  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (n, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((d, n), rng3)
  }
  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, rng2) = double(rng)
    val (d2, rng3) = double(rng2)
    val (d3, rng4) = double(rng3)
    ((d1, d2, d3), rng4)
  }
  def ints(count: Int)(rng: RNG): (List[Int], RNG) = if (count <= 0) (Nil, rng)
  else {
    val (n, rng2) = rng.nextInt
    val (list, rng3) = ints(count - 1)(rng2)
    (n :: list, rng3)
  }

  // combinators
  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, rng2) = ra(rng)
    val (b, rng3) = rb(rng2)
    (f(a, b), rng3)
  }

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = rng => fs.foldLeft((Nil: List[A], rng))((listRNG, random) => {
    val results = listRNG._1
    val rng = listRNG._2
    val (a, rng2) = random(rng)
    (a :: results, rng2)
  })
  
  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]) : Rand[B] = ???

  object withSequence {
    def ints(count: Int)(rng: RNG): (List[Int], RNG) = sequence(List.fill(count)(rng.int))(rng)
  }
  def positiveMax(n: Int): Rand[Int] = map(positiveInt)(_ % (n + 1))

  object withMap {
    def double: Rand[Double] = map(positiveInt)(_.toDouble / Int.MaxValue)
  }
  object withMap2 {
    def intDouble: Rand[(Int, Double)] = map2(positiveInt, double)((a, b) => (a, b))
    def doubleInt: Rand[(Double, Int)] = map(intDouble)(x => (x._2, x._1))
  }

}