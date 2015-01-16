package par

import java.util.concurrent.{TimeUnit, Future, ExecutorService}

/**
 * Created by gerrit on 03.01.15.
 */

object Par {
  type Par[A] = ExecutorService => Future[A]
  // Obtains the result of a parallel computation.
  def run[A](implicit es : ExecutorService)(pa : Par[A]) : Future[A] = pa(es)

  def unit[A](a: A) : Par[A] = (es : ExecutorService) => UnitFuture(a)
  // Wraps a computation to be done into a parallel computation (e.g. by starting a thread to compute it).
  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true
    def get(timeout: Long, units: TimeUnit) = get
    def isCancelled = false
    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  def lazyUnit[A](a: => A) : Par[A] = fork(unit(a))

  def map2[A,B,C](pa: => Par[A], pb : => Par[B])(f: (A,B) -> C) : Par[C] = ???
  // =?= unit(f(get(pa), get(pb)))

  def fork[A](pa : => Par[A]) : Par[A]
 }
