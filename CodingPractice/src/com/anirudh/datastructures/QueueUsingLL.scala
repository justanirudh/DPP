package com.anirudh.datastructures

/**
  * Created by anirudh on 12/11/16.
  */
class QueueUsingLL[T] extends LinkedList[T]{

//  override var head:Option[Node] = None
  var tail:Option[Node] = None

  def enqueue(elem:T) = {
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

  def dequeue() = {
    if(head.isEmpty && tail.isEmpty){
      println("cannot dequeue from an empty queue")
      null
    }
    else if(head == tail){ //1 element in queue
      val elem = head.get.elem
      head = None
      tail = None
      elem
    }
    else{
      val elem = head.get.elem
      head = head.get.next
      elem
    }
  }

  def printQueue() = {
    if(this.tail.isEmpty && this.head.isEmpty)
      println("empty queue")
    else {
      var ptr = head
      while(ptr.isDefined){
        print(ptr.get.elem + "->")
        ptr = ptr.get.next
      }
      print("NULL")
    }
  }

  def isEmpty = tail.isEmpty && head.isEmpty
}

object QueueUsingLL extends App{
  val q = new QueueUsingLL[(Int, Int)]
  q.enqueue((1,1))
//  q.enqueue((2,2))
//  q.enqueue((3,3))
//  q.enqueue((4,4))
//  q.printQueue()
  q.dequeue()
//  q.dequeue()
  q.printQueue()
}
