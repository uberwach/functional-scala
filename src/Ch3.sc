object Ch3 {
  import data._
  List.drop(List(1, 2, 3), 5)                     //> res0: data.List[Int] = List(3,2,1)
  List.dropWhile(List(1, 2, 3))(_ < 3)            //> res1: data.List[Int] = List(2,1)
  List.setHead(List(2, 2, 3), 1)                  //> res2: data.Cons[Int] = List(1,2,3)
  List.init(List(1, 2, 3))                        //> res3: Int = 3

  val firstFive = List(1, 2, 3, 4, 5)             //> firstFive  : data.List[Int] = List(1,2,3,4,5)
  List.sum2(firstFive)                            //> res4: Int = 15

  List.product2(List(1., 2., 3.))                 //> res5: Double = 6.0

  List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
                                                  //> res6: data.List[Int] = List(1,2,3)

  List.length(firstFive)                          //> res7: Int = 5

  List.foldLeft(firstFive, 0)(_ + _)              //> res8: Int = 15

  val bigList = List.reverse(List.genSequence(200, (x: Int) => x))
                                                  //> bigList  : data.List[Int] = List(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,1
                                                  //| 8,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43
                                                  //| ,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,
                                                  //| 69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,9
                                                  //| 4,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114
                                                  //| ,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133
                                                  //| ,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152
                                                  //| ,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171
                                                  //| ,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190
                                                  //| ,191,192,193,194,195,196,197,198,199,200)
  List.sum2(bigList)                              //> res9: Int = 20100

  List.reverse(List(1, 2, 3))                     //> res10: data.List[Int] = List(3,2,1)

  val abc = List("a", "b", "c")                   //> abc  : data.List[String] = List(a,b,c)
  List.foldLeft2(abc, "")((a, b) => a + a + b)    //> res11: String = aabaabc
  List.foldRight2(abc, "")((a, b) => a + b + b)   //> res12: String = abccbcc

  List.append2(List(1, 2), List(3, 4))            //> res13: data.List[Int] = List(1,2,3,4)

  List.flatten(List(List(1, 2), List(3, 4), List(5)))
                                                  //> res14: data.List[Int] = List(1,2,3,4,5)

  List.map(List(1, 2, 3))((x: Int) => x + 1)      //> res15: data.List[Int] = List(2,3,4)

  List.filter(bigList, (_: Int) % 2 == 1)         //> res16: data.List[Int] = List(1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,3
                                                  //| 5,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73,75,77,79,81,83,85
                                                  //| ,87,89,91,93,95,97,99,101,103,105,107,109,111,113,115,117,119,121,123,125,12
                                                  //| 7,129,131,133,135,137,139,141,143,145,147,149,151,153,155,157,159,161,163,16
                                                  //| 5,167,169,171,173,175,177,179,181,183,185,187,189,191,193,195,197,199)
  List.flatMap(List(1, 2, 3))(i => List(i, i))    //> res17: data.List[Int] = List(1,1,2,2,3,3)
  List.zip(List(1, 2, 3), List('a', 'b', 'c'))    //> res18: data.List[(Int, Char)] = List((1,a),(2,b),(3,c))
  List.combine(List(1, 2, 3), List(4, 5, 6))(_ + _)
                                                  //> res19: data.List[Int] = List(5,7,9)
  List.forall(List(1, 2, 3))(_ <= 3)              //> res20: Boolean = true
  List.forall(List(1, 2, 3))(_ < 3)               //> res21: Boolean = false
  List.hasSubsequence(bigList, List(48, 49, 50))  //> res22: Boolean = true

  import Tree._
  val tree = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
                                                  //> tree  : data.Branch[Int] = Branch(Leaf(1),Branch(Leaf(2),Leaf(3)))
  size(tree)                                      //> res23: Int = 5
  depth(tree)                                     //> res24: Int = 2
  maximum(tree)                                   //> res25: Int = 3
  map(tree)(x => x + 1)                           //> res26: data.Tree[Int] = Branch(Leaf(2),Branch(Leaf(3),Leaf(4)))

  size2(tree)                                     //> res27: Int = 5
  depth2(tree)                                    //> res28: Int = 2
  maximum2(tree)                                  //> res29: Int = 3
  map2(tree)(x => x + 1)                          //> res30: data.Tree[Int] = Branch(Leaf(2),Branch(Leaf(3),Leaf(4)))
}