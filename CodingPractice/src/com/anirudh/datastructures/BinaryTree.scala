package com.anirudh.datastructures

/**
  * Created by anirudh on 3/11/16.
  */

class TreeNode(var elem:Int, var left:Option[TreeNode], var right:Option[TreeNode])

class BinaryTree {

  var size = 0
  /*private*/ var root:Option[TreeNode] = None

  private def add(newNode:TreeNode, node:TreeNode):Unit = {
    val r = scala.util.Random.nextInt(10)
    if(r < 5 ){ //randomly adding to the tree
      if(node.left.isEmpty)
        node.left = Some(newNode)
      else
        add(newNode, node.left.get)
    }
    else{
      if(node.right.isEmpty)
        node.right = Some(newNode)
      else
        add(newNode, node.right.get)
    }
  }

  private def show(treeNode:Option[TreeNode]):String = {
    if(treeNode.isEmpty)
      "NULL"
    else
      treeNode.get.elem + " -> {"+ show(treeNode.get.left) + ", " + show(treeNode.get.right)+ "}"
  }

  def add(elem:Int):Unit = {
    val newNode = new TreeNode(elem, None, None)
    if(size == 0)
      root = Some(newNode)
    else
      add(newNode, root.get)
    size = size+1
  }

  def show():Unit = print(show(root))

}
