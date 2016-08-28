package com.anirudh.datastructures

/**
  * Created by anirudh on 2/6/16.
  */
object Stack extends App{

  class Stack {
    private var list = Seq[Int]()

    def push(i:Int):Stack = {
      list = i +: list
      this
    }

    def pop():Stack = {
      list = list.tail
     this
    }

    def print() = {
      for(i <- list){
        printf(i + "->")
      }
      printf("null")
    }

    def peek():Int = list.head
  }

  val stack = new Stack
  stack.push(1)
  stack.push(4)
  stack.push(5)
  stack.print()
  val s2 = stack
  s2.pop()
  s2.print()
  stack.print()
  println(stack.peek())

}
