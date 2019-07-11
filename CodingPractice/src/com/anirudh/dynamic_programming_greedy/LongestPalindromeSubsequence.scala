package com.anirudh.dynamic_programming_greedy

import com.anirudh.dynamic_programming_greedy.LongestCommonSubsequenceScala._

/**
  * Created by anirudh on 26/9/16.
  */
object LongestPalindromeSubsequence extends App{

  val input = "character"
  val inputReverse = input.reverse
  val longestCommonSubsequence = findLCSString(input, inputReverse)
  println(longestCommonSubsequence)

}
