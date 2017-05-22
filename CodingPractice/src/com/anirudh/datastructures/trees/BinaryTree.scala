package com.anirudh.datastructures.trees



class BinaryTree {

  var size = 0
  /*private*/ var root:Option[TreeNodeS] = None

  private def add(newNode:TreeNodeS, node:TreeNodeS):Unit = {
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

  private def show(TreeNodeS:Option[TreeNodeS]):String = {
    if(TreeNodeS.isEmpty)
      "NULL"
    else
      TreeNodeS.get.elem + " -> {"+ show(TreeNodeS.get.left) + ", " + show(TreeNodeS.get.right)+ "}"
  }

  def add(elem:Int):Unit = {
    val newNode = new TreeNodeS(elem, None, None)
    if(size == 0)
      root = Some(newNode)
    else
      add(newNode, root.get)
    size = size+1
  }

  def show():Unit = print(show(root))

}
