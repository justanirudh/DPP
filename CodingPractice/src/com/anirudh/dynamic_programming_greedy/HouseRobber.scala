package com.anirudh.dynamic_programming_greedy

/**
  * Created by anirudh on 13/11/16.
  */
/*
LeetCode – House Robber (Java)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
money you can rob tonight without alerting the police.
 */

//recurrence relation: amount_stashed_till[i] = max(amount_stashed_till[i-1], amount_stashed_till[i-2] + amount[i])
object HouseRobber extends App {

  def rob(amounts: Seq[Int]): Int = {
    if (amounts.isEmpty) 0
    else if (amounts.length == 1) amounts.head
    else if (amounts.length == 2) Math.max(amounts.head, amounts(1))
    else {
      val sums = Array.fill(amounts.length) {
        0
      }
      sums(0) = amounts.head
      sums(1) = Math.max(amounts.head, amounts(1))
      for (index <- 2 until amounts.length) {
        sums(index) = Math.max(sums(index - 1), sums(index - 2) + amounts(index))
      }
      sums(amounts.length - 1)
    }
  }

  //  val amounts = Seq(5, 7, 3, 3, 8, 9, 10, 9, 8, 4)
  val amounts = Seq(1, 3, 2, 1)
  //4
  val sum = rob(amounts)
  println(sum)

}
