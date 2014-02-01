object Ch5 {
	def if2[A](cond: Boolean)(onTrue: => A, onFalse: => A): A =
		if(cond) onTrue else onFalse      //> if2: [A](cond: Boolean)(onTrue: => A, onFalse: => A)A
		
		
	if2(false)(sys.error("fail"), 3)          //> res0: Int = 3
	
	import data.Stream._
	import data._
	val test = Stream(1,2,3)                  //> test  : data.Stream[Int] = data.Stream$$anon$2@74359b24
	test.toList                               //> res1: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
	
	test.take(2).toList                       //> res2: data.List[Int] = Cons(1,Cons(2,Nil))
	
	test.takeWhile(_ < 2).toList              //> res3: data.List[Int] = Cons(1,Nil)
	
	test.exists(_ > 2)                        //> res4: Boolean = true
	test.forAll(_ <= 3)                       //> res5: Boolean = true
	test.forAll(_ != 2)                       //> res6: Boolean = false
	
	test.takeWhile2(_ < 4).toList             //> res7: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
	ones.map(_*2).take(5).toList              //> res8: data.List[Int] = Cons(2,Cons(2,Cons(2,Cons(2,Cons(2,Nil)))))
	
	ones.take(30).filter(_ < 10).toList       //> res9: data.List[Int] = Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons
                                                  //| (1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Con
                                                  //| s(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Co
                                                  //| ns(1,Nil))))))))))))))))))))))))))))))
  Stream(1,2,3,4,5).filter(_ % 2 == 1).toList     //> res10: data.List[Int] = Cons(1,Cons(3,Cons(5,Nil)))
  
  def double[A](a : A) : Stream[A] = Stream(a,a)  //> double: [A](a: A)data.Stream[A]
  
  Stream(1,2,3).flatMap(double).toList            //> res11: data.List[Int] = Cons(1,Cons(1,Cons(2,Cons(2,Cons(3,Cons(3,Nil))))))
                                                  //| 
  ones.takeWhile(_ == 1)                          //> res12: data.Stream[Int] = data.Stream$$anon$2@1b3bae89
  ones.forAll(_ != 1)                             //> res13: Boolean = false
  
  fib.take(10).toList                             //> res14: data.List[Int] = Cons(0,Cons(1,Cons(1,Cons(2,Cons(3,Cons(5,Cons(8,Con
                                                  //| s(13,Cons(21,Cons(34,Nil))))))))))
    
  ones2.take(5).toList                            //> res15: data.List[Int] = Cons(1,Cons(1,Cons(1,Cons(1,Cons(1,Nil)))))
  constant2("a").take(5).toList                   //> res16: data.List[String] = Cons(a,Cons(a,Cons(a,Cons(a,Cons(a,Nil)))))
	fib2.take(5).toList                       //> res17: data.List[Int] = Cons(0,Cons(1,Cons(1,Cons(2,Cons(3,Nil)))))
}