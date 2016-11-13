package com.anirudh.datastructures

/**
  * Created by anirudh on 12/11/16.
  */
class LinkedList {

  class Node(val elem:Int, var next:Option[Node])

  var head:Option[Node] = None

  def add(elem:Int) = {
    val newNode = new Node(elem, None)
    if(head.isEmpty)
      head = Some(newNode)
    else{
      var curr = head.get
      while(curr.next.isDefined)
        curr = curr.next.get
      curr.next = Some(newNode)
    }
  }

  def delete(elem:Int) = {
    if(head.isEmpty){
      println("Empty Linked List. Cannot Delete")
    }
    else if(head.get.elem == elem) { //head is to be deleted
      head = head.get.next
    }
    else{
      var pred = head
      var curr = head.get.next
      while(curr.isDefined && curr.get.elem != elem){
        pred = curr
        curr = curr.get.next
      }
      if(curr.isEmpty){
        println("Could not find element "+elem+". cannot delete")
      }
      else{ //element found
        pred.get.next = curr.get.next
      }
    }
  }

  def printLL() = {
    var curr = head
    while(curr.isDefined){
      print(curr.get.elem + "->")
      curr = curr.get.next
    }
    print("null\n")
  }

}

object LinkedList extends App {
  val ll = new LinkedList
  ll.add(7)
  ll.add(6)
  ll.add(1)
  ll.add(5)
  ll.add(4)
  ll.printLL()
  ll.delete(7)
  ll.printLL()
  ll.delete(6)
  ll.printLL()

  val ll2 = new LinkedList
  ll2.add(4)
  ll2.add(3)
  ll2.add(2)
}
