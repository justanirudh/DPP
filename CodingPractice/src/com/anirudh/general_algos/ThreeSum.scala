package com.anirudh.general_algos

/**
  * Created by anirudh on 6/10/16.
  */

/*
*
* Problem:

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the
array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.

    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
* */
object ThreeSum extends App {

  def findThreeSums(arr: Seq[Int], target: Int): Seq[Seq[Int]] = {
    var result: Seq[Seq[Int]] = Seq()
    val arrLastTwoDropped = arr.dropRight(2).zipWithIndex
    for ((elemi, i) <- arrLastTwoDropped) {

      var j = i + 1
      var k = arr.size - 1

      while (j < k) {
        val elemj = arr(j)
        val elemk = arr(k)
        val sumijk = elemi + elemj + elemk

        if (sumijk == target) {
          result = result :+ Seq(elemi, elemj, elemk)
          println("i = " + arr.indexOf(elemi) + ", j = " + j + " k = " + k)
          j = j + 1
          k = k - 1
        }
        else if (sumijk > target) {
          k = k - 1
        }
        else
          j = j + 1
      }
    }
    result
  }

  val arr = Seq(-1, 0, 1, 2, -1, -4)
  val target = 0
  val threesums = findThreeSums(arr.sorted, target) /*.distinct*/
  println("-------------")
  for (result <- threesums) {
    for (r <- result) {
      print(r + " ")
    }
    println()
  }
}
