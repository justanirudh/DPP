package com.anirudh.algos

/**
  * Created by anirudh on 2/6/16.
  */
abstract class BinaryTree[+A] {
  def isEmpty: Boolean
  def isValid: Boolean
}

case object EmptyTree extends BinaryTree[Nothing] {
  def isEmpty = true
  def isValid = true
}

case class NonEmptyTree[A](
                            var data: A,
                            var left: BinaryTree[A],
                            var right: BinaryTree[A])
                          (implicit ord: Ordering[A]) extends BinaryTree[A] {
  def isEmpty = false
  def isValid: Boolean = {
    import ord._
    def isValidWith(f: A => Boolean, t: BinaryTree[A]): Boolean = t match {
      case NonEmptyTree(that, _, _) => f(that) && t.isValid
      case EmptyTree => true
    }
    isValidWith(data > _, left) && isValidWith(data < _, right)
  }
}

object Tree extends App {

  val t:NonEmptyTree[Int] = NonEmptyTree(4, NonEmptyTree(3, EmptyTree,EmptyTree),
    NonEmptyTree(6, EmptyTree, EmptyTree))
  println(t.isValid) //true
}