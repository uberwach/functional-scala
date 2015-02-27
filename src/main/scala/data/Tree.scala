package data

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(left, right) => 1 + size(left) + size(right)
  }

  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(n) => n
    case Branch(left, right) => maximum(left) max maximum(right)
  }

  def depth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 0
    case Branch(left, right) => 1 + (depth(left) max depth(right))
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Leaf(a) => Leaf(f(a))
    case Branch(left, right) => Branch(map(left)(f), map(right)(f))
  }

  def fold[A, B](tree: Tree[A], f: A => B)(g: (B, B) => B): B = tree match {
    case Leaf(a) => f(a)
    case Branch(left, right) => g(fold(left, f)(g), fold(right, f)(g))
  }

  // other functions implemented with fold
  def size2[A](tree: Tree[A]): Int = fold(tree, (a: A) => 1)((b1: Int, b2: Int) => b1 + b2 + 1)

  def maximum2(tree: Tree[Int]): Int = fold(tree, (a: Int) => a)((b1: Int, b2: Int) => b1 max b2)

  def depth2[A](tree: Tree[A]): Int = fold(tree, (a: A) => 0)((b1: Int, b2: Int) => (b1 max b2) + 1)

  def map2[A, B](tree: Tree[A])(f: A => B): Tree[B] = fold[A, Tree[B]](tree, (a: A) => Leaf(f(a)))((b1: Tree[B], b2: Tree[B]) => Branch(b1, b2))
}