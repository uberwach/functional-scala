package par

/**
 * Created by gerrit on 03.01.15.
 */
trait Par[A] {

}

object Par {
  // Obtains the result of a parallel computation.
  def get[A](pa : Par[A]) : A = ???
  // Wraps a computation to be done into a parallel computation (e.g. by starting a thread to compute it).
  def unit[A](a : => A) : Par[A] = ???
 }
