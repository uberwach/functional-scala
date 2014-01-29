object Ch3 {
   import data._
 
 List.drop(List(1,2,3),5)                         //> res0: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
 List.dropWhile(List(1,2,3))(_ < 3)               //> res1: data.List[Int] = Cons(2,Cons(1,Nil))
 List.setHead(List(2,2,3), 1)                     //> res2: data.Cons[Int] = Cons(1,Cons(2,Cons(3,Nil)))
 List.init(List(1,2,3))                           //> res3: Int = 3
 
 val firstFive = List(1,2,3,4,5)                  //> firstFive  : data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
 List.sum2(firstFive)                             //> res4: Int = 15
 List.product2(firstFive)                         //> res5: Int = 120
}