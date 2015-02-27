object Ch3 {

  import data._

  List.drop(List(1, 2, 3), 5)
  List.dropWhile(List(1, 2, 3))(_ < 3)
  List.setHead(List(2, 2, 3), 1)
  List.init(List(1, 2, 3))

  val firstFive = List(1, 2, 3, 4, 5)
  List.sum2(firstFive)

  List.product2(List(1., 2., 3.))

  List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))


  List.length(firstFive)

  List.foldLeft(firstFive, 0)(_ + _)

  val bigList = List.reverse(List.genSequence(200, (x: Int) => x))


  List.sum2(bigList)

  List.reverse(List(1, 2, 3))

  val abc = List("a", "b", "c")
  List.foldLeft2(abc, "")((a, b) => a + a + b)
  List.foldRight2(abc, "")((a, b) => a + b + b)

  List.append2(List(1, 2), List(3, 4))

  List.flatten(List(List(1, 2), List(3, 4), List(5)))


  List.map(List(1, 2, 3))((x: Int) => x + 1)

  List.filter(bigList, (_: Int) % 2 == 1)




  List.flatMap(List(1, 2, 3))(i => List(i, i))
  List.zip(List(1, 2, 3), List('a', 'b', 'c'))
  List.combine(List(1, 2, 3), List(4, 5, 6))(_ + _)

  List.forall(List(1, 2, 3))(_ <= 3)
  List.forall(List(1, 2, 3))(_ < 3)
  List.hasSubsequence(bigList, List(48, 49, 50))

  import Tree._

  val tree = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))

  size(tree)
  depth(tree)
  maximum(tree)
  map(tree)(x => x + 1)

  size2(tree)
  depth2(tree)
  maximum2(tree)
  map2(tree)(x => x + 1)
}