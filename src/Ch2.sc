object Ch1 {
 import Chapter1Exercises._
 (0 to 10).map(Fib).toString                      //> res0: String = Vector(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
 isSorted(Array(1,2,3), (a : Int,b : Int) => a < b)
                                                  //> res1: Boolean = true
 isSorted(Array(3,2,4), (a : Int,b : Int) => a < b)
                                                  //> res2: Boolean = false
 val sum : (Int,Int) => Int = (a,b) => a+b        //> sum  : (Int, Int) => Int = <function2>
 val inc1 = partial1(1,sum)                       //> inc1  : Int => Int = <function1>
 inc1(1)                                          //> res3: Int = 2
 uncurry(curry(sum))(1,1)                         //> res4: Int = 2
 
 import data._
 
 List.drop(List(1,2,3),5)                         //> res5: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
 List.dropWhile(List(1,2,3))(_ < 3)               //> res6: data.List[Int] = Cons(2,Cons(1,Nil))
 List.setHead(List(2,2,3), 1)                     //> res7: data.Cons[Int] = Cons(1,Cons(2,Cons(3,Nil)))
 List.init(List(1,2,3))                           //> res8: Int = 3
}