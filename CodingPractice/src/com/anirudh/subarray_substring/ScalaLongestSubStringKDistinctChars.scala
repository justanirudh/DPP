package com.anirudh.subarray_substring

/**
  * Created by anirudh on 28/10/16.
  */
/*
* Longest Substring with At Most K Distinct Characters

This is a problem asked by Google.

Given a string, find the longest substring that contains only two unique characters. For example, given
"abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".
* */
//TODO: this gets the longest subsequence, not a string
object ScalaLongestSubStringKDistinctChars extends App{

  //using library funcs
  def getLSSKDC(str:String, k:Int):String = {
    var hm = Map[Char, Int]()
    for (c <- str){ // O(n)
      if(hm.contains(c))
        hm = hm + (c -> (hm(c) + 1)) //update
      else
        hm = hm + (c -> 1) //addition
    }
    val mapSeq = hm.toSeq.sortBy(_._2) //O(n) if radix sort
    val distinctChars = mapSeq.takeRight(k).map(_._1) //O(n)
    var temp = ""
    for(c <- str){ //O(n)
      if(distinctChars.contains(c))
        temp = c + temp
    }
    temp.reverse //O(n)
  }
//O(n) time, O(n) space
  val str = "abcbbbbcccbdddadacb"
  val k = 2
  val result = getLSSKDC(str, k)
  println(result)
}
