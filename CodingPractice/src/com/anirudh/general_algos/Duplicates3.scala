package com.anirudh.general_algos

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

  def isDuplicate2(arr:Seq[Int], k:Int):(Boolean, Int) = { //O(n)
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

//  val k = 4 // false for 3
//  val isDup2 = isDuplicate2(arr, k)
//  println(isDup2._1, isDup2._2)

  //TODO: Logic looks fine. Does not work correctly
  def isDuplicate3(arr:Seq[Int], numDiff:Int, indexDiff:Int):Boolean = {
    val hashBuckets:Array[Array[Int]] = Array.fill(arr.max + 1){Array[Int]()}

    def atMostNumDiffs(arrIndex:Int):Boolean = {
      if(arrIndex == arr.size) false
      else {
        val hashLower = if(0 > arr(arrIndex) - numDiff) 0 else arr(arrIndex) - numDiff
        val hashUpper = if(hashBuckets.length - 1 < arr(arrIndex) + numDiff) hashBuckets.length - 1 else arr(arrIndex) + numDiff

        def atMostIndexDiffs(hashIndex:Int):Boolean = {
          if(hashIndex > hashUpper) false
          else if (hashBuckets(hashIndex).isEmpty){
            hashBuckets(hashIndex) = arrIndex +: hashBuckets(hashIndex)
            atMostIndexDiffs(hashIndex + 1)
          }
          else{
            def checkIndexDiff(hashBucketIndex:Int):Boolean = {
              if(hashBucketIndex == hashBuckets(hashIndex).length) false
              else if(arrIndex - hashBuckets(hashIndex)(hashBucketIndex) <= indexDiff) true
              else checkIndexDiff(hashBucketIndex + 1)
            }
            val indexDiffWithinRange = checkIndexDiff(0)
            if(indexDiffWithinRange) indexDiffWithinRange
            else {
              //add and go to next element
              hashBuckets(hashIndex) = arrIndex +: hashBuckets(hashIndex)
              atMostIndexDiffs(hashIndex + 1)
            }
          }
        }
        val found = atMostIndexDiffs(hashLower)
        if(found) found
        else atMostNumDiffs(arrIndex + 1)
      }
    }
    atMostNumDiffs(0)
  }

  val arr2 = Seq(3, 6, 9)

  val numDiff = 2
  val indexDiff = 1 // 7 and 12
  println(isDuplicate3(arr2, numDiff, indexDiff))

}
