package com.anirudh.algos

/**
  * Created by anirudh on 1/10/16.
  */
object CountingSort extends App{

  def getCountingSorted(arr:Seq[Int]):Seq[Int] = { //arr is A
  val bufferSize = arr.max //arr[j] belong to 1 to k
  val buffer = Array.fill(bufferSize){0}
    for(i <- arr.indices){ //C
      buffer(arr(i)-1) = buffer(arr(i)-1) + 1
    }
    buffer foreach println
    println("----------------------")
    for(i <- 1 until bufferSize){ //C' - cumulative values
      buffer(i) = buffer(i - 1) + buffer(i)
    }
    buffer foreach println
    println("----------------------")
    val sortedArray = Array.fill(arr.size){0} //B
    for(i <- (arr.size - 1) to 0 by -1){
      sortedArray(buffer(arr(i)-1)-1) = arr(i)
      buffer(arr(i)-1) = buffer(arr(i)-1) - 1
    }
    sortedArray
  }

  val arr = Seq(4,1,3,4,3, 5, 9, 10, 1, 2, 12, 2, 9)
  val sortedArr = getCountingSorted(arr)
  sortedArr foreach println
}
