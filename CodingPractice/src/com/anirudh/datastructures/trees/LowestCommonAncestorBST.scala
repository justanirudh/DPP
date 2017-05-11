package com.anirudh.datastructures.trees

/**
  * Created by anirudh on 10/9/16.
  */
//Binary Search Tree
//No need of stack or anything. Iterate for the 2 elements together

case class ElementsNotFoundException(elem1:Int, elem2:Int) extends Exception("Element was not found: " + elem1 + "," + elem2)
case class EmptyTreeException() extends Exception("Tree is empty")

object LeastCommonAncestor extends App{

  class BSTWithLCA extends BinarySearchTree {

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


  val tree = new BSTWithLCA
  tree.add(19)
  tree.add(7)
  tree.add(43)
  tree.add(3)
  tree.add(2)
  tree.add(5)
  tree.add(11)
  tree.add(17)
  tree.add(13)

  val lca = tree.findLCA(2, 13)
  println("\nLowest Common Ancestor is: " + lca)




}
