package com.anirudh.subarray_substring

/**
  * Created by anirudh on 19/10/16.
  */
/*
* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example, given [100, 4, 200, 1, 3, 2], the longest consecutive elements sequence should be [1, 2, 3, 4]. Its length is 4.

Your algorithm should run in O(n) complexity
* */
object ScalaLongestConsecutiveSequence extends App{

  //using NO library functions
  //assuming no repetitions in input array
  def findLongestConseq(input:Seq[Int]):Seq[Int] = {
    var max = 0
    for(elem <- input){
      if(elem > max)
        max = elem
    }
    val hashArray = Array.fill(max+1){0} // +1 so that last number is mapped correctly
    for(elem <- input){ //filling the hashtable
      hashArray(elem) = 1
    }
    var sizeEndingHere = 0
    var maxSizeEndingHere = 0
    var arrayEndingHere:Seq[Int] = Seq()
    var maxArrayEndingHere:Seq[Int] = Seq()

    for((elem, i) <- hashArray.view.zipWithIndex){ //elem can be 0 or 1; i is the actual element from original array

      if(elem == 0){
        sizeEndingHere = 0
        arrayEndingHere = Seq()
      }
      else{//1
        if( i == 0 || hashArray(i-1) == 0){ //first element || previous element is 0
          sizeEndingHere = 1
          arrayEndingHere = Seq(i)
        }
        else { // curr is 1 and prev elem is 1
          sizeEndingHere = sizeEndingHere + 1
          arrayEndingHere = arrayEndingHere :+ i
        }

        if(sizeEndingHere > maxSizeEndingHere){
          maxSizeEndingHere = sizeEndingHere
          maxArrayEndingHere = arrayEndingHere
        }
      }

    }
    maxArrayEndingHere
  }

  val input = Seq(100,4,200,1, 3,2, 201, 202)
  val longestConsecutiveSequence = findLongestConseq(input)
  longestConsecutiveSequence foreach println
}
