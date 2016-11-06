package com.anirudh.hackerrank

import com.anirudh.hackerrank.LongestCommonSubsequence._

/**
  * Created by anirudh on 26/9/16.
  */
object LongestPalindromeSubsequence extends App{

  val input = "character"
  val inputReverse = input.reverse
  val longestCommonSubsequence = findLCSString(input, inputReverse)
  println(longestCommonSubsequence)

}
