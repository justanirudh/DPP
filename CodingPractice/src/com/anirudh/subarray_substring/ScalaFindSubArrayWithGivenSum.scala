package com.anirudh.subarray_substring

/**
  * Created by anirudh on 7/10/16.
  */
/*
* Find subarray with given sum | Set 1 (Nonnegative Numbers)
Given an unsorted array of nonnegative integers, find a continuous subarray which adds to a given number.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found
* */
object ScalaFindSubArrayWithGivenSum extends App{

  //Incrementing index (end) till we get sum == or > target. Then incrementing another index
  //from start that decrements sum till we get == or < sum. Like a sliding window.
  def findSubArray(arr:Seq[Int], target:Int):Seq[Int] = {
    var sum = 0
    var start = 0
    var end = 0
    while(sum != target && end < arr.size){
      if(sum < target){
        sum = sum + arr(end)
        end = end + 1
      }
      else{
        sum = sum - arr(start)
        start = start + 1
      }
    }
    if(end == arr.size)
      Seq()
    else
      arr.slice(start, end)
  }


  val arr = Seq(1, 4, 20, 3, 10, 5)
  val target = 33
  val subarr = findSubArray(arr, target)
  for(elem <- subarr)
    print(elem + " ")
  println()

  val arr2 = Seq(1, 4, 0, 0, 3, 10,5 )
  val target2 = 7
  val subarr2 = findSubArray(arr2, target2)
  for(elem <- subarr2)
    print(elem + " ")
}
