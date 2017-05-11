package com.anirudh.datastructures.trees

/**
  * Created by anirudh on 3/11/16.
  */


class AlmostBST extends BinaryTree{ //Not a BST

  private def add(newNode:TreeNode, node:TreeNode):Unit = {

    if(newNode.elem == 14){
      node.left = Some(newNode)
    }
    else if (newNode.elem == 23){
      node.right = Some(newNode)
    }
    else if(newNode.elem == 25){
      node.left.get.right = Some(newNode)
    }
  }

  override def add(elem:Int):Unit = {
    val newNode = new TreeNode(elem, None, None)
    if(size == 0)
      root = Some(newNode)
    else
      add(newNode, root.get)
    size = size+1
  }
}

object CheckIfaBinaryTreesIsaBST extends App{

  val BIGNUMBER = 10000

  //Checks if the given binary tree is also a binary search tree
  def checkBSTAux(curr:Option[TreeNode], min:Int, max:Int): Boolean = {
    if(curr.isEmpty)
      true
    else {
      val left = curr.get.left
      val right = curr.get.right
      (curr.get.elem > min && curr.get.elem < max) && // check if min < curr < max
      (if(left.isEmpty) true else left.get.elem < curr.get.elem) && // check if left < curr
        (if(right.isEmpty) true else right.get.elem > curr.get.elem) && // check if curr < right
        checkBSTAux(left, min, curr.get.elem) && checkBSTAux(right, curr.get.elem, max) //update min and max and call recursively
    }
  }

  //passing dummy values for root and checking in Aux fn to skip root.
  //min and max values to make sure ALL nodes in left of curr node are less than node and ALL node in right of curr node
  //are greater than curr node
  def checkIfBinaryTreeaBST(tree:BinaryTree):Boolean = checkBSTAux(tree.root, -1*BIGNUMBER, BIGNUMBER)

  val bst = new BinarySearchTree
  bst.add(20)
  bst.add(14)
  bst.add(23)
  bst.add(25)
  bst.show()
  val isBST = checkIfBinaryTreeaBST(bst)
  println("\n"+isBST)

  val bt = new BinaryTree
  bt.add(20)
  bt.add(14)
  bt.add(23)
  bt.add(25)
  bt.show()
  val isBST2 = checkIfBinaryTreeaBST(bt)
  println("\n"+isBST)


  val abst = new AlmostBST
  abst.add(20)
  abst.add(14)
  abst.add(23)
  abst.add(25)
  abst.show()
  val isBST3 = checkIfBinaryTreeaBST(abst)
  print("\n"+isBST3)
}
