package com.anirudh.datastructures.trees

/**
 * Created by anirudh on 3/11/16.
 */

class BinarySearchTree extends BinaryTree {

  private def add(newNode: TreeNodeS, node: TreeNodeS): Unit = {
    if (newNode.elem < node.elem) {
      if (node.left.isEmpty)
        node.left = Some(newNode)
      else
        add(newNode, node.left.get)
    }
    else if (newNode.elem > node.elem) {
      if (node.right.isEmpty)
        node.right = Some(newNode)
      else
        add(newNode, node.right.get)
    }
    else {
      println("Element already present in the tree. Not adding again")
    }
  }

  override def add(elem: Int): Unit = {
    val newNode = new TreeNodeS(elem, None, None)
    if (size == 0)
      root = Some(newNode)
    else
      add(newNode, root.get)
    size = size + 1
  }

  private def search(elem: Int, node: TreeNodeS): Option[TreeNodeS] = {
    if (elem < node.elem) {
      if (node.left.isEmpty)
        None
      else
        search(elem, node.left.get)
    }
    else if (elem > node.elem) {
      if (node.right.isEmpty)
        None
      else
        search(elem, node.right.get)
    }
    else
      Some(node)
  }

  def search(elem: Int): Option[TreeNodeS] = {
    if (root.isEmpty)
      None
    else
      search(elem, root.get)
  }

  private def getParent(elem: Int, node: TreeNodeS): Option[TreeNodeS] = { // in its public mthod, check for elem = root
    if (elem < node.elem) {
      if (node.left.isEmpty)
        None
      else if (node.left.get.elem == elem)
        Some(node)
      else
        getParent(elem, node.left.get)
    }
    else { //only other option is elem > node.elem; no elem = node.elem option will exist
      if (node.right.isEmpty)
        None
      else if (node.right.get.elem == elem)
        Some(node)
      else
        getParent(elem, node.right.get)
    }
  }

  def delete(elem: Int): Unit = {
    if (root.isEmpty)
      println("Can't delete from empty tree")
    else {
      if (elem == root.get.elem) { //deleting the root
        if (root.get.left.isEmpty && root.get.right.isEmpty) //root is leaf
          root = None
        else if (root.get.left.isDefined && root.get.right.isEmpty) //left node exists
          root = root.get.left
        else if (root.get.left.isEmpty && root.get.right.isDefined) //right node exists
          root = root.get.right
        else {
          var succ = root.get.right.get
          while (succ.left.isDefined) //go left to get min element of child right subtree
            succ = succ.left.get
          if (succ != root.get.right.get) { //child's right child is not its succ
            val succParent = getParent(succ.elem, root.get).get
            root.get.elem = succ.elem //replace child with its successor
            succParent.left = succ.right //point succ's parent to succ's right Child
          }
          else { //child's right child is its succ
            root.get.elem = succ.elem
            root.get.right = succ.right
          }
        }
      }
      else { //deleting a non-root
        val parent: Option[TreeNodeS] = getParent(elem, root.get)
        parent match {
          case None => println("Element not found")
          case Some(p) =>
            if (p.left.isEmpty || p.left.get.elem != elem) { //right is the to-be-deleted child node
              val child = p.right.get
              if (child.left.isEmpty && child.right.isEmpty) //case 1: child is a leaf node
                p.right = None
              else if (child.left.isDefined && child.right.isEmpty) //case 2: child has 1 left child node
                p.right = child.left
              else if (child.left.isEmpty && child.right.isDefined) //case 2: child has 1 right child node
                p.right = child.right
              else { //case 3: both children of child exist; same for both ifs as no p involved
                //modified getSuccessor method
                var succ = child.right.get
                while (succ.left.isDefined) //go left to get min element of child right subtree
                  succ = succ.left.get
                if (succ != child.right.get) { //child's right child is not its succ
                  val succParent = getParent(succ.elem, root.get).get
                  child.elem = succ.elem //replace child with its successor
                  succParent.left = succ.right //point succ's parent to succ's right Child
                }
                else { //child's right child is its succ
                  child.elem = succ.elem
                  child.right = succ.right
                }
              }
            }
            else { //left is to-be-deleted child; all p.rights become p.lefts, that's it
              val child = p.left.get
              if (child.left.isEmpty && child.right.isEmpty) //case 1: child is a leaf node
                p.left = None
              else if (child.left.isDefined && child.right.isEmpty) //case 2: child has 1 left child node
                p.left = child.left
              else if (child.left.isEmpty && child.right.isDefined) //case 2: child has 1 right child node
                p.left = child.right
              else { //case 3: both children of child exist; same for both ifs as no p involved
                //modified getSuccessor method
                var succ = child.right.get
                while (succ.left.isDefined) //go left to get min element of child right subtree
                  succ = succ.left.get
                if (succ != child.right.get) { //child's right child is not its succ
                  val succParent = getParent(succ.elem, root.get).get
                  child.elem = succ.elem //replace child with its successor
                  succParent.left = succ.right //point succ's parent to succ's right Child
                }
                else { //child's right child is its succ
                  child.elem = succ.elem
                  child.right = succ.right
                }
              }
            }
        }
      }
    }
  }

}

object BinarySearchTree extends App {
  val tree = new BinarySearchTree
  tree.add(19)
  tree.add(7)
  tree.add(43)
  tree.add(3)
  tree.add(2)
  tree.add(5)
  tree.add(11)
  tree.add(17)
  tree.add(13)
  tree.add(32)
  tree.add(55)
  tree.add(23)
  tree.add(53)
  tree.add(56)

  tree.show()
  tree.search(18) match {
    case None => println("Not found")
    case Some(x) => println(x + " found")
  }
  println(tree.getParent(43, tree.root.get).get.elem) //19 ; can access private vars
  tree.delete(19)
  tree.show()
}
