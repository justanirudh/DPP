package com.anirudh.general_algos

/**
  * Created by anirudh on 22/9/16.
  */

/*
* The Egyptian Cafe recently implemented a new online order system where customers can place their orders online and have it delivered to their doorstep. The new system is very popular and the restaurant is getting hundreds of orders during peak hours, causing customers to wait a long time before their order gets processed.

They are particularly worried about losing their loyal base of most frequent customers. A new intern implemented a simple priority system for the order queue such that the customers who have ordered the most times from the restaurant receive the highest priority and have their order picked up first. Each order is now assigned a priority, which is the number of past orders they've placed. This priority is capped at 20 such that all customers who have ordered at least 20 times receive the same priority. The system operates as follows.

The first order J in queue is taken from the queue.
If there is some order in the queue with a higher priority than order J, then move J to the end of the queue without processing it.
Otherwise, process the order J. That is, send it to the kitchen and do not put it back in the queue.
This has helped improve service to the most important customers but first time customers may have to wait for a very long time and have no transparency into how long their order will take.

You decide to write a program to figure this out. The program will be given the current queue (as a list of priorities) as well as the position of your order in the queue, and must then calculate how long it will take until your order is processed, assuming that no additional orders will be added to the queue. To simplify matters, we assume that processing an order always takes exactly 2 minutes, and that adding and removing orders from the queue is instantaneous.


Input Specifications

The first line will will contain two integers separated by a space n m, where 1 ≤ n ≤ 100 is the number of orders in the queue and 0 ≤ m ≤ n-1 is the position of your order. The first position in the queue is number 0, the second is number 1, and so on.

The second line will contain n integers each in the range 0 to 20, giving the priorities of the orders in the queue. The first integer gives the priority of the first order, the second integer the priority of the second order, and so on.


Output Specifications

Print a single integer; the number of minutes until your order is processed, assuming that no additional orders will be placed.


Sample Input/Output

INPUT
1 0
5
OUTPUT
2
EXPLANATION
There is only one order in the queue so it will take 2 minutes to be processed.
INPUT
4 2
1 2 3 4
OUTPUT
4
EXPLANATION
Your order is third (at position 2) with a priority of 3. Only one order is of higher priority so your order will be processed second with the total time being 4 minutes.
INPUT
6 0
1 1 9 1 1 1
OUTPUT
10
EXPLANATION
Your order is first in the queue with a priority of 1. Your order and the next will be placed on the back of the queue, then the priority 9 order is processed followed by three priority 1 orders which will get to the front of the queue before yours. The total time to process your order will be 10 minutes.*/

import scala.io.StdIn.readLine

object EgyptianCafe extends App {

  def findTime(queue:Seq[(Int, Int)], myOrder:(Int, Int), time: Int):Int = {
    val maxPrior = queue.maxBy(_._1)._1
    val qFirst = queue.head
    if(qFirst._1 == maxPrior && qFirst._1 == myOrder._1 && qFirst._2 == myOrder._2 ){
      time + 2
    }
    else if (qFirst._1 == maxPrior){
      findTime(queue.tail, myOrder,time + 2)
    }
    else{
      val newQueue = queue.tail :+ queue.head
      findTime(newQueue, myOrder, time)
    }
  }

  var queueSizeAndPos:Seq[String] = readLine().split(" ") //not req

  val prioritiesWithIndex = readLine().split(" ").map(_.toInt).toSeq.zipWithIndex

  val pos = queueSizeAndPos(1).toInt

  val myOrder = prioritiesWithIndex(pos)

  val time = findTime(prioritiesWithIndex, myOrder, 0)

  println(time)

}
