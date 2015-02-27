object Ch4 {

  import data.{Option, none, some, List}

  val op1 = data.some(1)
  op1.map(a => a + 1)
  val op2: Option[Int] = data.none
  op2.map(a => a + 1)

  val f = (a: Int) => if (a == 0) data.none else data.some(1./ a)


  op1.flatMap(f)
  data.some(0) flatMap f

  op1.getOrElse(0)
  op2.getOrElse(0)
  op1.orElse(data.some(0))
  op2.orElse(data.some(0))
  op1.filter(a => a > 2)
  op1.filter(a => a <= 2)

  import data.Option._

  val deadlifts = Seq(220.0, 145.0, 227.5)
  mean(deadlifts)
  variance(deadlifts)
  mean(Nil)
  variance(Nil)

  Option.map2(data.some(1), data.some(2))(_ + _)
  Option.map2(data.some(1), data.none: Option[Int])(_ + _)


  Option.sequence(List(some(1), some(2)))
  Option.sequence(List(some(1), none, some(3)))

  Option.sequence2(List(some(1), some(2)))
  Option.sequence2(List(some(1), none, some(3)))

  import data.Either._

  val e1 = safeDiv(1., 2.)
  val e2 = safeDiv(2., 0.)
  val e3 = safeDiv(1., 4.)
  e1.map2(e2)((a, b) => a + b)
  e1.map2(e3)((a, b) => a + b)
  data.Either.sequence(List(e1, e2, e3))

  data.Either.sequence(List(e1, e3))
}