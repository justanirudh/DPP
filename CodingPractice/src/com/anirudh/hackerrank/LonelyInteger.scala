package com.anirudh.hackerrank

/**
  * Created by anirudh on 22/10/16.
  */
//Given an array with double entries for every number except one, find that one unique lonely number.
object LonelyInteger extends App{

  def lonelyInteger(arr: Array[Int]): Int = {
    val size = arr.max
    val hashTable = Array.fill[Int](size + 1){0}
    for(num <- arr){
      hashTable(num) = hashTable(num) + 1
    }
    def getUnique(index:Int):Int = {
      if(index == size + 1) // no uniques
        -1
      else if (hashTable(index) == 1)
        index
      else
        getUnique(index + 1)
    }
    getUnique(0)
  }

  val arr = Array(1, 3, 4, 2, 1, 3, 4, 5, 2)
  val uniq = lonelyInteger(arr)
  println(uniq)
}
