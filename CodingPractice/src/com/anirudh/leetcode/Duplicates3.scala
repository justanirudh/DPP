package com.anirudh.leetcode

/**
  * Created by anirudh on 10/11/16.
  */
/*
LeetCode – Contains Duplicate III (Java)

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*
*
* LeetCode – Contains Duplicate II (Java)

Given an array of integers and an integer k, return true if and only if there are two distinct indices i and j in the
array such that nums[i] = nums[j] and the difference between i and j is at most k.
 */
object Duplicates3 extends App{

  def isDuplicate2(arr:Seq[Int], k:Int):(Boolean, Int) = {
    val hashArr = Array.fill(arr.max + 1) {-1}

    def atMostK(index:Int):(Boolean, Int) = {
      if(index == arr.size) (false, -1) //if reached end of arr, false
      else if(hashArr(arr(index)) == -1){ //if previously hashArr has -1, just put the index of the current arr element
        hashArr(arr(index)) = index
        atMostK(index + 1)
      }
      else{ //if hashArr is not empty at arr(index)
        if(index - hashArr(arr(index)) <= k) (true, arr(index)) //if curr index - stored index < k, true
        else{ //else replace and continue. repalce because the next elem, if any, will be further away from currently stored, hence, replace
          hashArr(arr(index)) = index
          atMostK(index + 1)
        }
      }
    }
    atMostK(0)
  }

  val arr = Seq(1,2,4,5,6,7,12,1,4,7,10,6)
  val k = 4 // false for 3
  val isDup2 = isDuplicate2(arr, k)
  println(isDup2._1, isDup2._2)
}
