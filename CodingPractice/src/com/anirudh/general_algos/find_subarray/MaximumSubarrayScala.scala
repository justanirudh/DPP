package com.anirudh.general_algos.find_subarray

/**
  * Created by anirudh on 2/12/16.
  */
/*
Largest Sum Contiguous Subarray
Write an efficient C program to find the sum of contiguous subarray within a one-dimensional array of numbers
which has the largest sum.
 */
object MaximumSubarrayScala extends App{

	def lSCS(arr:Array[Int]):Int = {
		var currsum = 0
		var maxsum = 0
		for(i <- arr.indices){
			currsum = currsum + arr(i)
			if(currsum < 0)
				currsum = 0
			if(currsum > maxsum)
				maxsum = currsum
		}
		maxsum
	}

	val arr = Array(-2, -3, 4, -1, -2, 1, 5, -3)
	println(lSCS(arr)) //7 {4,-1,2,1,5}
}
