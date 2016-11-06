package com.anirudh.algos

import scala.collection.mutable

/**
  * Created by anirudh on 24/6/16.
  */
//CTCIChap9Q1 - Dynamic programming: top-down
object DPStepsProblem extends App {

  val cache = new mutable.HashMap[Int, Int]()

  def calculateNumberOfWaysWithoutDP(steps:Int):Int = {
    if(steps < 0)
      0
    else if (steps == 0 )
      1
    else
      calculateNumberOfWaysWithoutDP(steps - 1) +
      calculateNumberOfWaysWithoutDP(steps - 2) +
      calculateNumberOfWaysWithoutDP(steps - 3)
  }

  def calculateNumberOfWaysWithDP(steps:Int):Int = {
    if(steps < 0)
      0
    else if (steps == 0 )
      1
    else if (cache.contains(steps))
      cache(steps)
    else {
      val ways = calculateNumberOfWaysWithDP(steps - 1) +
        calculateNumberOfWaysWithDP(steps - 2) +
        calculateNumberOfWaysWithDP(steps - 3)
      cache.put(steps, ways)
      ways
    }

  }

  val n = 30

  val start = System.currentTimeMillis()
  val ways = calculateNumberOfWaysWithoutDP(n)
  val end = System.currentTimeMillis()
  println("ways: " + ways)
  println("time taken without DP: " + (end - start))
  println("-------------------")
  val start2 = System.currentTimeMillis()
  val waysDP = calculateNumberOfWaysWithDP(n)
  val end2 = System.currentTimeMillis()
  println("ways: " + waysDP)
  println("time taken with DP: " + (end2 - start2))

}
