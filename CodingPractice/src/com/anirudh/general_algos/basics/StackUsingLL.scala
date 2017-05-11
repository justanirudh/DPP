package com.anirudh.general_algos.basics

/**
  * Created by anirudh on 12/11/16.
  */
class StackUsingLL extends LinkedList[Int]{

  def push(elem:Int) = {
    val newNode = new Node(elem, None)
    if(head.isEmpty)
      head = Some(newNode)
    else{
      newNode.next = head
      head = Some(newNode)
    }
  }

  def pop():Int = {
    if(head.isEmpty){
      println("Stack empty. Nothing to pop")
      -1
    }
    else{
      val elem = head.get.elem
      head = head.get.next
      elem
    }
  }

  def peek:Int = {
    if(head.isEmpty){
      println("Stack empty. Nothing to peek")
      -1
    }
    else head.get.elem
  }

}
