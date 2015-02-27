package data

case class State[S, +A](run: S => (A, S)) {

  import State._

  def apply(s: S) = run(s)

  def flatMap[B](g: A => State[S, B]): State[S, B] = (s: S) => {
    val (a, s2) = this(s)
    g(a)(s2)
  }

  def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = {
    val sab = flatMap(a => sb.flatMap(b => unit((a, b))))
    sab.flatMap(ab => unit(f(ab._1, ab._2)))
  }

}

object State {
  implicit def toState[S, A](run: S => (A, S)): State[S, A] = State(run)

  def unit[S, A](a: A): State[S, A] = (s: S) => (a, s)

  def sequence[S, A](fs: List[State[S, A]]): State[S, List[A]] = (s: S) => fs.foldLeft((Nil: List[A], s))((listState, random) => {
    val results = listState._1
    val state = listState._2
    val (a, state2) = random(state)
    (Cons(a, results), state2)
  })

  def get[S]: State[S, S] = ((s: S) => (s, s))

  def set[S](s: S): State[S, S] = ((s2: S) => (s, s))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))
  } yield ()

}

object stateExample {

  sealed trait Input

  case object Coin extends Input

  case object Turn extends Input

  case class Machine(locked: Boolean, candies: Int, coins: Int)

  def flipTuple[A, B](ab: (A, B)): (B, A) = (ab._2, ab._1)

  def simulateMachine(inputs: List[Input]): State[Machine, Int] = {
    def stateTransistion(in: List[Input], s: (Machine, Int)): (Machine, Int) = in match {
      case Nil => s
      case Cons(inp, inps) => {
        val (machine, coins): (Machine, Int) = s

        if (machine.candies == 0) stateTransistion(inps, s)
        else {
          inp match {
            case Coin => if (!machine.locked) stateTransistion(inps, s) // nothing
            else {
              val newCoins = machine.coins + 1
              stateTransistion(inps, (machine.copy(locked = false, coins = newCoins), newCoins))
            }
            case Turn => if (machine.locked) stateTransistion(inps, s) // nothing
            else {
              stateTransistion(inps, (machine.copy(locked = true, candies = (machine.candies - 1)), machine.coins))
            }
          }
        }
      }
    }

    State((m: Machine) =>
      flipTuple(stateTransistion(inputs, (m, m.coins))))
  }
}
