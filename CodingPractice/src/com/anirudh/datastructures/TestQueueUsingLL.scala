package com.anirudh.datastructures

/**
  * Created by anirudh on 16/11/16.
  */
object TestQueueUsingLL extends App{

  val q = new QueueUsingLL[(Int, Int)]
  q.enqueue((0,0))
  q.dequeue()
  q.enqueue((1,0))
  q.dequeue()
  q.printLL()
}
