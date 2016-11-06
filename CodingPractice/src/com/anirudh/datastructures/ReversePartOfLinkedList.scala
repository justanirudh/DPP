package com.anirudh.datastructures

import com.anirudh.datastructures.LinkedListAddTwoNumbers.LinkedList

/**
  * Created by anirudh on 10/10/16.
  */
case class ListnotLongEnoughException(msg:String) extends Exception(msg)
object ReversePartOfLinkedList extends App{

  class LLWithReversalFunc extends LinkedList{

    def reverse(start:Int, end:Int) = {
      if(this.head.isEmpty)
        throw ListnotLongEnoughException("")

      var pred:Option[Node] = None
      var curr:Option[Node] = this.head
      var succ:Option[Node] = None

      //finding the start of the list that needs to be changed
      var countStart = 1
      while(countStart != start){
        if(curr.isDefined){
          curr = curr.get.next
          if(pred.isEmpty)
            pred = this.head
          else
            pred = pred.get.next
        }
        else
          throw ListnotLongEnoughException("")
        countStart = countStart + 1
      }
      // Now curr is at the starting node and pred is one node before it
      val head = pred //as later on pred's next would point to head of reversed list
      val tail = curr // tail's next would point to the start of the part of list beyond he revered part

      //Finding the end Node
      var stopNodeCounter = 1
      var stopNode:Option[Node] = this.head
      while(stopNodeCounter != end){
        stopNode = stopNode.get.next
        stopNodeCounter = stopNodeCounter +1
      }

      pred = curr
      curr = curr.get.next
      succ = curr.get.next

      while(curr != stopNode){ //TODO: need to check for 'none' pointers here
        curr.get.next = pred
        pred = curr
        curr = succ
        succ = succ.get.next
      }
      curr.get.next = pred
      head.get.next = curr
      tail.get.next = succ
    }
  }

  val ll = new LLWithReversalFunc
  ll.add(0)
  ll.add(1)
  ll.add(2)
  ll.add(3)
  ll.add(4)
  ll.add(5)
  ll.add(6)
  ll.add(7)
  ll.add(8)
  ll.printLL()
  ll.reverse(5, 9)
  ll.printLL()
}
