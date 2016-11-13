package com.anirudh.datastructures

/**
  * Created by anirudh on 12/11/16.
  */
class QueueUsingLL extends LinkedList{

  var tail:Option[Node] = None

  def enqueue(elem:Int) = {
    val newNode = new Node(elem, None)
    if(head.isEmpty && tail.isEmpty){
      head = Some(newNode)
      tail = Some(newNode)
    }
    else{
      tail.get.next = Some(newNode)
      tail = tail.get.next
    }
  }

  def dequeue():Int = {
    if(head.isEmpty && tail.isEmpty){
      println("cannot dequeue from an empty queue")
      -1
    }
    else{
      val elem = head.get.elem
      head = head.get.next
      elem
    }
  }

}
