object Ch3 {
  import data._
  List.drop(List(1, 2, 3), 5)                     //> res0: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
  List.dropWhile(List(1, 2, 3))(_ < 3)            //> res1: data.List[Int] = Cons(2,Cons(1,Nil))
  List.setHead(List(2, 2, 3), 1)                  //> res2: data.Cons[Int] = Cons(1,Cons(2,Cons(3,Nil)))
  List.init(List(1, 2, 3))                        //> res3: Int = 3

  val firstFive = List(1, 2, 3, 4, 5)             //> firstFive  : data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
  List.sum2(firstFive)                            //> res4: Int = 15

  List.product2(List(1., 2., 3.))                 //> res5: Double = 6.0

  List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
                                                  //> res6: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))

  List.length(firstFive)                          //> res7: Int = 5

  List.foldLeft(firstFive, 0)(_ + _)              //> res8: Int = 15

  val bigList = List.reverse(List.genSequence(200, (x: Int) => x))
                                                  //> bigList  : data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Cons(7
                                                  //| ,Cons(8,Cons(9,Cons(10,Cons(11,Cons(12,Cons(13,Cons(14,Cons(15,Cons(16,Cons(
                                                  //| 17,Cons(18,Cons(19,Cons(20,Cons(21,Cons(22,Cons(23,Cons(24,Cons(25,Cons(26,C
                                                  //| ons(27,Cons(28,Cons(29,Cons(30,Cons(31,Cons(32,Cons(33,Cons(34,Cons(35,Cons(
                                                  //| 36,Cons(37,Cons(38,Cons(39,Cons(40,Cons(41,Cons(42,Cons(43,Cons(44,Cons(45,C
                                                  //| ons(46,Cons(47,Cons(48,Cons(49,Cons(50,Cons(51,Cons(52,Cons(53,Cons(54,Cons(
                                                  //| 55,Cons(56,Cons(57,Cons(58,Cons(59,Cons(60,Cons(61,Cons(62,Cons(63,Cons(64,C
                                                  //| ons(65,Cons(66,Cons(67,Cons(68,Cons(69,Cons(70,Cons(71,Cons(72,Cons(73,Cons(
                                                  //| 74,Cons(75,Cons(76,Cons(77,Cons(78,Cons(79,Cons(80,Cons(81,Cons(82,Cons(83,C
                                                  //| ons(84,Cons(85,Cons(86,Cons(87,Cons(88,Cons(89,Cons(90,Cons(91,Cons(92,Cons(
                                                  //| 93,Cons(94,Cons(95,Cons(96,Cons(97,Cons(98,Cons(99,Cons(100,Cons(101,Cons(10
                                                  //| 2,Cons(103,Cons(104,Cons(105,Cons(106,Cons(107,Cons(108,Cons(109,Cons(110,Co
                                                  //| ns(111,Cons(112,Cons(113,Cons(114,Cons(115,Cons(116,Cons(117,Cons(118,Cons(1
                                                  //| 19,Cons(120,Cons(121,Cons(122,Cons(123,Cons(124,Cons(125,Cons(126,Cons(127,C
                                                  //| ons(128,Cons(129,Cons(130,Cons(131,Cons(132,Cons(133,Cons(134,Cons(135,Cons(
                                                  //| 136,Cons(137,Cons(138,Cons(139,Cons(140,Cons(141,Cons(142,Cons(143,Cons(144,
                                                  //| Cons(145,Cons(146,Cons(147,Cons(148,Cons(149,Cons(150,Cons(151,Cons(152,Cons
                                                  //| (153,Cons(154,Cons(155,Cons(156,Cons(157,Cons(158,Cons(159,Cons(160,Cons(161
                                                  //| ,Cons(162,Cons(163,Cons(164,Cons(165,Cons(166,Cons(167,Cons(168,Cons(169,Con
                                                  //| s(170,Cons(171,Cons(172,Cons(173,Cons(174,Cons(175,Cons(176,Cons(177,Cons(17
                                                  //| 8,Cons(179,Cons(180,Cons(181,Cons(182,Cons(183,Cons(184,Cons(185,Cons(186,Co
                                                  //| ns(187,Cons(188,Cons(189,Cons(190,Cons(191,Cons(192,Cons(193,Cons(194,Cons(1
                                                  //| 95,Cons(196,Cons(197,Cons(198,Cons(199,Cons(200,Nil)))))))))))))))))))))))))
                                                  //| ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
                                                  //| ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
                                                  //| )))))))))))))))))))))))
  List.sum2(bigList)                              //> res9: Int = 20100

  List.reverse(List(1, 2, 3))                     //> res10: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))

  val abc = List("a", "b", "c")                   //> abc  : data.List[String] = Cons(a,Cons(b,Cons(c,Nil)))
  List.foldLeft2(abc, "")((a, b) => a + a + b)    //> res11: String = aabaabc
  List.foldRight2(abc, "")((a, b) => a + b + b)   //> res12: String = abccbcc

  List.append2(List(1, 2), List(3, 4))            //> res13: data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))

  List.flatten(List(List(1, 2), List(3, 4), List(5)))
                                                  //> res14: data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))

  List.map(List(1, 2, 3))((x: Int) => x + 1)      //> res15: data.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))

  List.filter(bigList, (_: Int) % 2 == 1)         //> res16: data.List[Int] = Cons(1,Cons(3,Cons(5,Cons(7,Cons(9,Cons(11,Cons(13,C
                                                  //| ons(15,Cons(17,Cons(19,Cons(21,Cons(23,Cons(25,Cons(27,Cons(29,Cons(31,Cons(
                                                  //| 33,Cons(35,Cons(37,Cons(39,Cons(41,Cons(43,Cons(45,Cons(47,Cons(49,Cons(51,C
                                                  //| ons(53,Cons(55,Cons(57,Cons(59,Cons(61,Cons(63,Cons(65,Cons(67,Cons(69,Cons(
                                                  //| 71,Cons(73,Cons(75,Cons(77,Cons(79,Cons(81,Cons(83,Cons(85,Cons(87,Cons(89,C
                                                  //| ons(91,Cons(93,Cons(95,Cons(97,Cons(99,Cons(101,Cons(103,Cons(105,Cons(107,C
                                                  //| ons(109,Cons(111,Cons(113,Cons(115,Cons(117,Cons(119,Cons(121,Cons(123,Cons(
                                                  //| 125,Cons(127,Cons(129,Cons(131,Cons(133,Cons(135,Cons(137,Cons(139,Cons(141,
                                                  //| Cons(143,Cons(145,Cons(147,Cons(149,Cons(151,Cons(153,Cons(155,Cons(157,Cons
                                                  //| (159,Cons(161,Cons(163,Cons(165,Cons(167,Cons(169,Cons(171,Cons(173,Cons(175
                                                  //| ,Cons(177,Cons(179,Cons(181,Cons(183,Cons(185,Cons(187,Cons(189,Cons(191,Con
                                                  //| s(193,Cons(195,Cons(197,Cons(199,Nil))))))))))))))))))))))))))))))))))))))))
                                                  //| ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
  List.flatMap(List(1, 2, 3))(i => List(i, i))    //> res17: data.List[Int] = Cons(1,Cons(1,Cons(2,Cons(2,Cons(3,Cons(3,Nil))))))
                                                  //| 
  List.zip(List(1, 2, 3), List('a', 'b', 'c'))    //> res18: data.List[(Int, Char)] = Cons((1,a),Cons((2,b),Cons((3,c),Nil)))
  List.combine(List(1, 2, 3), List(4, 5, 6))(_ + _)
                                                  //> res19: data.List[Int] = Cons(5,Cons(7,Cons(9,Nil)))
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