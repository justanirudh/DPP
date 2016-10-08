package com.anirudh.datastructures

/**
  * Created by anirudh on 7/10/16.
  */
/*
* Leetcode – Add Two Numbers (Java)

You are given two linked lists representing two non-negative numbers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Input: (7 -> 6 -> 1 -> 5 -> 4) + (4 -> 3 -> 2)
Output: 1 -> 0 -> 4 -> 5 -> 4
* */
object LinkedListAddTwoNumbers extends App{

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
      var curr = head
      if(head.isEmpty){
        println("Empty Linked List. Cannot Delete")
      }
      else{
        var pred = head
        curr = curr.get.next
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

    def addLL(ll:LinkedList):LinkedList = {
      val sumLL = new LinkedList
      if(head.isEmpty && ll.head.isEmpty)
        sumLL
      else if (head.isDefined && ll.head.isEmpty)
        this
      else if (head.isEmpty && ll.head.isDefined)
        ll
      else{
        var currThis = head
        var elemThis = 0
        var currThat = ll.head
        var elemThat = 0
        var carryOver = 0
        var sum = 0
        var actualSum = 0
        while(currThis.isDefined && currThat.isDefined){
          elemThis = currThis.get.elem
          elemThat = currThat.get.elem
          actualSum = elemThis + elemThat + carryOver
          sum = if(actualSum <10) actualSum else actualSum-10
          carryOver = if(actualSum <10) 0 else 1
          sumLL.add(sum)
          currThis = currThis.get.next
          currThat = currThat.get.next
        }
        if(currThis.isEmpty && currThat.isEmpty)
          sumLL
        else if (currThis.isDefined && currThat.isEmpty){
          while(currThis.isDefined){
            sumLL.add(currThis.get.elem)
            currThis = currThis.get.next
          }
          sumLL
        }
        else{
          while(currThat.isDefined){
            sumLL.add(currThat.get.elem)
            currThat = currThat.get.next
          }
          sumLL
        }
      }
    }

  }

  val ll = new LinkedList
  ll.add(7)
  ll.add(6)
  ll.add(1)
  ll.add(5)
  ll.add(4)
  val ll2 = new LinkedList
  ll2.add(4)
  ll2.add(3)
  ll2.add(2)
  val sumLL = ll.addLL(ll2)
  sumLL.printLL()
}
