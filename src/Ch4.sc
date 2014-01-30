object Ch4 {
import data.Option
	val op1 = data.some(1)                    //> op1  : data.some[Int] = some(1)
	op1.map(a => a+1)                         //> res0: data.Option[Int] = some(2)
	val op2 : Option[Int] = data.none         //> op2  : data.Option[Int] = none
	op2.map(a => a+1)                         //> res1: data.Option[Int] = none
	
	val f = (a : Int) => if (a == 0) data.none else data.some(1./a)
                                                  //> f  : Int => Product with Serializable with data.Option[Double] = <function1>
                                                  //| 
	op1.flatMap(f)                            //> res2: data.Option[Double] = some(1.0)
  data.some(0) flatMap f                          //> res3: data.Option[Double] = none
  
  op1.getOrElse(0)                                //> res4: Int = 1
	op2.getOrElse(0)                          //> res5: Int = 0
	op1.orElse(data.some(0))                  //> res6: data.Option[Int] = some(1)
	op2.orElse(data.some(0))                  //> res7: data.Option[Int] = some(0)
	op1.filter(a => a > 2)                    //> res8: data.Option[Int] = none
	op1.filter(a => a <= 2)                   //> res9: data.Option[Int] = some(1)
	
	import data.Option._
	val deadlifts = Seq(220.0,145.0,227.5)    //> deadlifts  : Seq[Double] = List(220.0, 145.0, 227.5)
	mean(deadlifts)                           //> res10: data.Option[Double] = some(197.5)
	variance(deadlifts)                       //> res11: data.Option[Double] = some(1387.5)
	mean(Nil)                                 //> res12: data.Option[Double] = none
	variance(Nil)                             //> res13: data.Option[Double] = none
}