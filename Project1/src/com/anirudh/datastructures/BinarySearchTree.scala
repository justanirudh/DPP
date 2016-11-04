package com.anirudh.datastructures

/**
  * Created by anirudh on 3/11/16.
  */

class BinarySearchTree extends BinaryTree{

//  var size = 0
//  var root:Option[TreeNode] = None

//  class TreeNode(val elem:Int, var left:Option[TreeNode], var right:Option[TreeNode])

  //TODO: implement delete
  private def add(newNode:TreeNode, node:TreeNode):Unit = {
    if(newNode.elem < node.elem){
      if(node.left.isEmpty)
        node.left = Some(newNode)
      else
        add(newNode, node.left.get)
    }
    else if (newNode.elem > node.elem){
      if(node.right.isEmpty)
        node.right = Some(newNode)
      else
        add(newNode, node.right.get)
    }
    else{
      println("Element already present in the tree. Not adding again")
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
