package data

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case some(a) => some(f(a))
    case none => data.none
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this map f getOrElse none

  def getOrElse[B >: A](default: => B): B = this match {
    case some(a) => a
    case none => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this.flatMap[Option[A]]((a: A) => some(some(a))).getOrElse(ob)
 
  def filter(f: A => Boolean) : Option[A] = this.flatMap(a => if(f(a)) some(a) else none)
  
  def lift[A,B](f: A => B) : Option[A] => Option[B] = _ map f
}
case class some[+A](get: A) extends Option[A]
case object none extends Option[Nothing]

// Chapter 4
object Option {
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) none
    else some(xs.sum / xs.length)
    
  def variance(xs: Seq[Double]) : Option[Double] = {
      val mOpt = mean(xs)
      mOpt.flatMap {m => 
        	mean(xs.map(x => math.pow(x-m,2)))
        }
    }
        
    
}