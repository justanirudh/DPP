package com.anirudh.algos

/**
  * Created by anirudh on 10/9/16.
  */
//Binary Search Tree
//No need of stack or anything. Iterate for the 2 elements together

case class ElementsNotFoundException(elem1:Int, elem2:Int) extends Exception("Element was not found: " + elem1 + "," + elem2)
case class EmptyTreeException() extends Exception("Tree is empty")

object LeastCommonAncestor extends App{

  class Tree{

    var size = 0
    var root:Option[TreeNode] = None

    class TreeNode(val elem:Int, var left:Option[TreeNode], var right:Option[TreeNode])

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

    private def show(treeNode:Option[TreeNode]):String = {
      if(treeNode.isEmpty)
        "NULL"
      else
        treeNode.get.elem + " -> {"+ show(treeNode.get.left) + ", " + show(treeNode.get.right)+ "}"
    }

    private def findLCA(elem1:Int, elem2:Int, curr:TreeNode): Int ={
      //curr is ancestor till now
      if((elem1 == curr.elem) ||
        (elem2 == curr.elem) ||
        (elem1 < curr.elem && elem2 > curr.elem) ||
        (elem1 > curr.elem && elem2 < curr.elem))
        curr.elem
      else if (elem1 < curr.elem && elem2 < curr.elem) {
        if(curr.left.isEmpty)
          throw ElementsNotFoundException(elem1, elem2)
        else
          findLCA(elem1,elem2, curr.left.get)
      }
      else {
        if(curr.right.isEmpty)
          throw ElementsNotFoundException(elem1, elem2)
        else
          findLCA(elem1,elem2, curr.right.get)
      }
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

    def findLCA(elem1:Int, elem2:Int):Int = {
      if(size == 0)
        throw EmptyTreeException()
      else{
        if(elem1 == elem2)
          elem1
        else{
          findLCA(elem1, elem2, root.get)
        }
      }
    }

  }

  val tree = new Tree
  tree.add(19)
  tree.add(7)
  tree.add(43)
  tree.add(3)
  tree.add(2)
  tree.add(5)
  tree.add(11)
  tree.add(17)
  tree.add(13)

  tree.show()
  val lca = tree.findLCA(2, 13)
  println("Lowest Common Ancestor is: " + lca)


}
