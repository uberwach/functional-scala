package par

import java.util.concurrent._

object Par {
  type Par[A] = ExecutorService => Future[A]

  // Obtains the result of a parallel computation.
  def run[A](pa : Par[A])(implicit es: ExecutorService) : Future[A] = pa(es)

  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  // Models a computation that is already done with the value.
  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true

    def get(timeout: Long, units: TimeUnit) = get

    def isCancelled = false

    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  private def failed[A] = new FailedFuture[A]()

  private class FailedFuture[A] extends Future[A] {
    def isDone = true

    def get = ??? // or throw new InterruptedException()?

    def get(timeout: Long, units: TimeUnit) = ??? // or throw new InterruptedException()?

    def isCancelled = true

    def cancel(evenIfRunning: Boolean): Boolean = true
  }

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def asyncF[A,B](f: A => B) : A => Par[B] = a => lazyUnit(f(a))

  def map2[A, B, C](pa: => Par[A], pb: => Par[B])(f: (A, B) => C): Par[C] = (es : ExecutorService) => {
    try {
      val timeBefore = System.currentTimeMillis()
      val a = pa(es).get(1000, TimeUnit.MILLISECONDS)
      val timeAfter = System.currentTimeMillis()
      val b = pb(es).get(1000 - (timeAfter - timeBefore), TimeUnit.MILLISECONDS)
      UnitFuture(f(a, b))
    } catch {
      case _ : TimeoutException => failed
      case _ : Throwable => failed
    }
  }

  def map[A,B](pa: => Par[A])(f: A => B) : Par[B] = map2(pa, unit(())) {(a,_) => f(a)}

  def sortPar[A : Ordering](pas: Par[List[A]]) : Par[List[A]] = map(pas)(_.sorted)

  def fork[A](pa: => Par[A]): Par[A] = es => es.submit(new Callable[A] {
    override def call(): A = pa(es).get
  })

  def get[A](pa: => Par[A]): A = {
    implicit val es = Executors.newFixedThreadPool(8)
    run(pa).get
  }

  def sequence[A](pas: List[Par[A]]) : Par[List[A]] = {
    val parList = pas.foldLeft(unit(List[A]())) { (coll, pa) =>
      map2(coll, pa)((as, a) => a :: as)
    }
    map(parList)(_.reverse)
  }
  
  def parMap[A,B](as: List[A])(f: A => B) : Par[List[B]] = {
    val pbs: List[Par[B]] = as map asyncF(f)
    sequence(pbs)
  }

  def parFilter[A](as: List[A])(p: A => Boolean) : Par[List[A]] = fork {
    map(lazyUnit(as))(_ filter p)
  }
}