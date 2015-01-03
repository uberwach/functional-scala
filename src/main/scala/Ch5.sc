object Ch5 {
	def if2[A](cond: Boolean)(onTrue: => A, onFalse: => A): A =
		if(cond) onTrue else onFalse      //> if2: [A](cond: Boolean)(onTrue: => A, onFalse: => A)A
		
		
	if2(false)(sys.error("fail"), 3)          //> res0: Int = 3
	
	import data.Stream._
	import data._
	val test = Stream(1,2,3)                  //> test  : data.Stream[Int] = data.Stream$$anon$2@5803eab9
	test.toList                               //> res1: data.List[Int] = List(1,2,3)
	
	test.take(2).toList                       //> res2: data.List[Int] = List(1,2)
	
	test.takeWhile(_ < 2).toList              //> res3: data.List[Int] = List(1)
	
	test.exists(_ > 2)                        //> res4: Boolean = true
	test.forAll(_ <= 3)                       //> res5: Boolean = true
	test.forAll(_ != 2)                       //> res6: Boolean = false
	
	test.takeWhile2(_ < 4).toList             //> res7: data.List[Int] = List(1,2,3)
	ones.map(_*2).take(5).toList              //> res8: data.List[Int] = List(2,2,2,2,2)
	
	ones.take(30).filter(_ < 10).toList       //> res9: data.List[Int] = List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                                                  //| 1,1,1,1,1,1)
  Stream(1,2,3,4,5).filter(_ % 2 == 1).toList     //> res10: data.List[Int] = List(1,3,5)
  
  def double[A](a : A) : Stream[A] = Stream(a,a)  //> double: [A](a: A)data.Stream[A]
  
  Stream(1,2,3).flatMap(double).toList            //> res11: data.List[Int] = List(1,1,2,2,3,3)
  ones.takeWhile(_ == 1)                          //> res12: data.Stream[Int] = data.Stream$$anon$2@1b333004
  ones.forAll(_ != 1)                             //> res13: Boolean = false
  
  fib.take(10).toList                             //> res14: data.List[Int] = List(0,1,1,2,3,5,8,13,21,34)
   
  ones2.take(5).toList                            //> res15: data.List[Int] = List(1,1,1,1,1)
  constant2("a").take(5).toList                   //> res16: data.List[String] = List(a,a,a,a,a)
	fib2.take(5).toList                       //> res17: data.List[Int] = List(0,1,1,2,3)
	
	fib2.map2{_*2}.take(5).toList             //> res18: data.List[Int] = List(0,2,2,4,6)
	fib2.take2(5).toList                      //> res19: data.List[Int] = List(0,1,1,2,3)
	fib2.takeWhile3(_ < 20).toList            //> res20: data.List[Int] = List(0,1,1,2,3,5,8,13)
	
	fib2.zip(ones).take(10).toList            //> res21: data.List[(Int, Int)] = List((0,1),(1,1),(1,1),(2,1),(3,1),(5,1),(8,1
                                                  //| ),(13,1),(21,1),(34,1))
  
  Stream.zipAll(Stream(1,2,3,4),Stream("a","b","c")).toList
                                                  //> res22: data.List[(data.Option[Int], data.Option[String])] = List((some(1),so
                                                  //| me(a)),(some(2),some(b)),(some(3),some(c)),(some(4),none))
  
  
  startsWith(Stream(1,2,3,4),Stream(1,2,3))       //> res23: Boolean = true
  startsWith(Stream(1,2,3),Stream(1,2,3,4))       //> res24: Boolean = false
  startsWith(Stream(1,2),Stream(2))               //> res25: Boolean = false
  Stream(1,2,3).tails.map(_.toList).toList        //> res26: data.List[data.List[Int]] = List(List(1,2,3),List(2,3),List(3),Nil)
                                                  //| 
  hasSubsequence(fib2.take(10),Stream(1,2,3,5))   //> res27: Boolean = true
  hasSubsequence(Stream(1,2,3),Stream(2,3))       //> res28: Boolean = true
  hasSubsequence(Stream(1,2,3,4),Stream(1,3))     //> res29: Boolean = false
  
  Stream(1,2,3).scanLeft(0)(_ + _).toList         //> res30: data.List[Int] = List(0,1,3,6)
  Stream(1,2,3).scanRight(0)(_ + _).toList        //> res31: data.List[Int] = List(6,5,3,0)
}