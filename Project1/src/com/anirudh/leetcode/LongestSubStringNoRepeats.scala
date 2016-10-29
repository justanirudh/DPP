package com.anirudh.leetcode

import scala.collection.immutable.HashMap

/**
  * Created by anirudh on 28/10/16.
  */
/*
* LeetCode – Longest Substring Without Repeating Characters (Java)

Given a string, find the length of the longest substring without repeating characters. For example, the longest
substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest
substring is "b", with the length of 1.
* */
object LongestSubStringNoRepeats extends App{

  def noRepeats(str:String):String = {
    var nr = ""
    var map = new HashMap[Char, Boolean]()
    for (s <- str){
      if(!map.contains(s))
        nr = nr + s
      map = map + (s -> true)
    }
    nr
  }

  val str = "abcabcdbbd"
  val noReps = noRepeats(str)
  print(noReps)
}
