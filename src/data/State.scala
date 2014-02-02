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
  
  
  def sequence[S,A](fs: List[State[S,A]]): State[S,List[A]] = (s : S) => fs.foldLeft((Nil: List[A], s))((listState, random) => {
    val results = listState._1
    val state = listState._2
    val (a, state2) = random(state)
    (Cons(a,results), state2)
  })
  
}
