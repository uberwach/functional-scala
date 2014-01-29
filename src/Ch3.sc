object Ch3 {
   import data._
 List.drop(List(1,2,3),5)                         //> res0: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
 List.dropWhile(List(1,2,3))(_ < 3)               //> res1: data.List[Int] = Cons(2,Cons(1,Nil))
 List.setHead(List(2,2,3), 1)                     //> res2: data.Cons[Int] = Cons(1,Cons(2,Cons(3,Nil)))
 List.init(List(1,2,3))                           //> res3: Int = 3
 
 val firstFive = List(1,2,3,4,5)                  //> firstFive  : data.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil)))))
 List.sum2(firstFive)                             //> res4: Int = 15
 
 List.product2(List(1.,2.,3.))                    //> res5: Double = 6.0
 
 List.foldRight(List(1,2,3),Nil:List[Int])(Cons(_,_))
                                                  //> res6: data.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
                                                  
 List.length(firstFive)                           //> res7: Int = 5
 
 List.foldLeft(firstFive,0)(_ + _)                //> res8: Int = 15
 
 val bigList = List.genSequence(200, (x : Int) => x)
                                                  //> bigList  : data.List[Int] = Cons(200,Cons(199,Cons(198,Cons(197,Cons(196,Con
                                                  //| s(195,Cons(194,Cons(193,Cons(192,Cons(191,Cons(190,Cons(189,Cons(188,Cons(18
                                                  //| 7,Cons(186,Cons(185,Cons(184,Cons(183,Cons(182,Cons(181,Cons(180,Cons(179,Co
                                                  //| ns(178,Cons(177,Cons(176,Cons(175,Cons(174,Cons(173,Cons(172,Cons(171,Cons(1
                                                  //| 70,Cons(169,Cons(168,Cons(167,Cons(166,Cons(165,Cons(164,Cons(163,Cons(162,C
                                                  //| ons(161,Cons(160,Cons(159,Cons(158,Cons(157,Cons(156,Cons(155,Cons(154,Cons(
                                                  //| 153,Cons(152,Cons(151,Cons(150,Cons(149,Cons(148,Cons(147,Cons(146,Cons(145,
                                                  //| Cons(144,Cons(143,Cons(142,Cons(141,Cons(140,Cons(139,Cons(138,Cons(137,Cons
                                                  //| (136,Cons(135,Cons(134,Cons(133,Cons(132,Cons(131,Cons(130,Cons(129,Cons(128
                                                  //| ,Cons(127,Cons(126,Cons(125,Cons(124,Cons(123,Cons(122,Cons(121,Cons(120,Con
                                                  //| s(119,Cons(118,Cons(117,Cons(116,Cons(115,Cons(114,Cons(113,Cons(112,Cons(11
                                                  //| 1,Cons(110,Cons(109,Cons(108,Cons(107,Cons(106,Cons(105,Cons(104,Cons(103,Co
                                                  //| ns(102,Cons(101,Cons(100,Cons(99,Cons(98,Cons(97,Cons(96,Cons(95,Cons(94,Con
                                                  //| s(93,Cons(92,Cons(91,Cons(90,Cons(89,Cons(88,Cons(87,Cons(86,Cons(85,Cons(84
                                                  //| ,Cons(83,Cons(82,Cons(81,Cons(80,Cons(79,Cons(78,Cons(77,Cons(76,Cons(75,Con
                                                  //| s(74,Cons(73,Cons(72,Cons(71,Cons(70,Cons(69,Cons(68,Cons(67,Cons(66,Cons(65
                                                  //| ,Cons(64,Cons(63,Cons(62,Cons(61,Cons(60,Cons(59,Cons(58,Cons(57,Cons(56,Con
                                                  //| s(55,Cons(54,Cons(53,Cons(52,Cons(51,Cons(50,Cons(49,Cons(48,Cons(47,Cons(46
                                                  //| ,Cons(45,Cons(44,Cons(43,Cons(42,Cons(41,Cons(40,Cons(39,Cons(38,Cons(37,Con
                                                  //| s(36,Cons(35,Cons(34,Cons(33,Cons(32,Cons(31,Cons(30,Cons(29,Cons(28,Cons(27
                                                  //| ,Cons(26,Cons(25,Cons(24,Cons(23,Cons(22,Cons(21,Cons(20,Cons(19,Cons(18,Con
                                                  //| s(17,Cons(16,Cons(15,Cons(14,Cons(13,Cons(12,Cons(11,Cons(10,Cons(9,Cons(8,C
                                                  //| ons(7,Cons(6,Cons(5,Cons(4,Cons(3,Cons(2,Cons(1,Nil)))))))))))))))))))))))))
                                                  //| ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
                                                  //| ))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
                                                  //| )))))))))))))))))))))))
 List.sum2(bigList)                               //> res9: Int = 20100
 
 List.reverse(List(1,2,3))                        //> res10: data.List[Int] = Cons(3,Cons(2,Cons(1,Nil)))
}