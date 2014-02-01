object Ch5 {
	def if2[A](cond: Boolean)(onTrue: => A, onFalse: => A): A =
		if(cond) onTrue else onFalse      //> if2: [A](cond: Boolean)(onTrue: => A, onFalse: => A)A
		
		
	if2(false)(sys.error("fail"), 3)          //> res0: Int = 3
	
	import data.Stream._
	import data._
	val test = Stream(1,2,3)                  //> test  : data.Stream[Int] = data.Stream$$anon$2@5176ade8
	test.toList                               //> res1: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
	
	test.take(2).toList                       //> res2: data.List[Int] = Cons(1,Cons(2,Nil))
	
	test.takeWhile(_ < 2).toList              //> res3: data.List[Int] = Cons(1,Nil)
	
	test.exists(_ > 2)                        //> res4: Boolean = true
	test.forAll(_ <= 3)                       //> res5: Boolean = true
	test.forAll(_ != 2)                       //> res6: Boolean = false
	
	test.takeWhile2(_ < 4).toList             //> res7: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
	ones.map(_*2).take(5).toList              //> res8: data.List[Int] = Cons(2,Cons(2,Cons(2,Cons(2,Cons(2,Nil)))))
}