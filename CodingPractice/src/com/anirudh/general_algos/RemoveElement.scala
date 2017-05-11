package com.anirudh.general_algos

/**
  * Created by anirudh on 20/11/16.
  */
/*
LeetCode – Remove Element (Java)

Given an array and a value, remove all instances of that value in place and return the new length.
(Note: The order of elements can be changed. *It doesn't matter what you leave beyond the new length.*)

LeetCode – Move Zeroes (Java)

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
object RemoveElement extends App{

  def removeElem(arr:Array[Int], elem:Int) = {
    var i= 0
    var j = 0
    while(j < arr.length){
      if(arr(j) != elem){
       arr(i) = arr(j)
       i = i + 1
      }
      j = j + 1
    }
    (arr, i)
  }

  val arr = Array(3,5,4,6,8,4,2,9,1,4,7,4) //length: 12
  val elem = 4

  val newArr = removeElem(arr, elem)
  println("'length' of new array: " + newArr._2)
  for(elem <- newArr._1)
    print(elem + " ")

  val arr2 = Array(0, 1, 0, 3, 12)

  def moveZeroes(arr:Array[Int]) = {
    val (arrMod, length) = removeElem(arr, 0)
    for (i <- length until arrMod.length) {
      arrMod(i) = 0
    }
    arrMod
  }

  val newArr2 = moveZeroes(arr2)
  println()
  for(elem <- newArr2)
    print(elem + " ")

}
