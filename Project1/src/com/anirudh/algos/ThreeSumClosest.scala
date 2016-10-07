package com.anirudh.algos

/**
  * Created by anirudh on 6/10/16.
  */
/*
* Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
* Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
* */

object ThreeSumClosest extends App{

  def findThreeSumsClosest(arr:Seq[Int], target:Int): Int/*((Int, Int, Int), Int)*/ ={
    val arrLastTwoDropped = arr.dropRight(2).zipWithIndex
    var min = Int.MaxValue
    for((elemi, i) <- arrLastTwoDropped){

      var j = i + 1
      var k  = arr.size - 1

      while(j < k){
        val elemj = arr(j)
        val elemk = arr(k)
        val sumijk = elemi + elemj + elemk
        if( sumijk == target){
          min = 0 //can be returned by putting this whole loop as a function
        }
        else if (sumijk > target){
          min = Math.min(min, Math.abs(sumijk-target))
          k = k - 1
        }
        else
          min = Math.min(min, Math.abs(sumijk-target))
          j = j + 1
      }
    }
    min
  }

  val arr = Seq( -1, 2 ,1 ,-4)
  val target = 0
  val threesums = findThreeSumsClosest(arr.sorted, target)
  println("threesums: " + threesums)

}
