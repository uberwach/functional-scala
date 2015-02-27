package data

sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case l@Left(_) => l
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case l@Left(_) => l
  }

  // unsure about the semantics
  def orElse[EE >: E, B](b: => Either[EE, B]): Either[EE, B] = this flatMap (x => b)

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
    val g: Either[EE, B => C] = this map ((a: A) => ((b: B) => f(a, b)))
    b flatMap ((bval: B) => g.map(_(bval)))
  }
}

case class Left[+E](value: E) extends Either[E, Nothing]

// convention: this is the failure case
case class Right[+A](value: A) extends Either[Nothing, A]


object Either {
  // inefficient though
  // and a little ugly probably...
  // goes through the list 3 times at worst, could be done in one pass through.
  // TODO: optimize to one pass through
  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    val fa = as map f
    // find a failure
    val e = Option.find(fa)(x => x match {
      case Left(e) => true
      case Right(_) => false
    })
    e match {
      case some(error) => error.map(b => List(b))
      // no failure found? then unwrap all the Bs
      case none => Right(fa flatMap (y => y match {
        case Left(_) => Nil
        case Right(v) => List(v)
      }))
    }
  }

  def sequence[E, A](as: List[Either[E, A]]): Either[E, List[A]] = traverse(as)(x => x)

  def safeDiv(x: Double, y: Double): Either[Exception, Double] =
    try {
      if (y == 0) throw new ArithmeticException
      Right(x / y)
    } catch {
      case e: Exception => Left(e)
    }
}