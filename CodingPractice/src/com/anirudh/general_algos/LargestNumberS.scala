package com.anirudh.general_algos

/**
  * Created by paanir on 12/17/16.
  */
//Given an array of numbers, find the largest number you can make from them
//Eg. [50, 213, 9] -> 950213
object LargestNumberS extends App{

  val arr = Seq(50, 213, 9)

  val ans = arr.map(num => (num.toString.head.toInt, num)).sortBy(_._1).reverse.map(tuple => tuple._2).mkString("")
  println(ans)
}
