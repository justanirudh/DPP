package com.anirudh.algos

/**
  * Created by anirudh on 25/9/16.
  */
object LongestIncreasingSubsequence extends App{

  def findLIS(nums:Seq[Int]):(Seq[Int], Int) = {
    //bottom-up approach
    def findLISAux(nums:Seq[Int]):(Seq[Int], Int) = {
      //initializing 1st seq
      var allSeqs = Seq[(Seq[Int], Int)](( Seq(nums.head), 1))

      for(i <- 1 until nums.size ){ //starting from 2nd element of nums
        var maxSeq = Seq[Int](nums(i))
        var maxSize = 1
        for(j <- 0 until i){
          //comparing whether the num is < the current num && the length of the array increases
          if(nums(j) < nums(i) && allSeqs(j)._2 + 1 > maxSize){
            maxSeq = nums(i) +: allSeqs(j)._1 //so that prepending is O(1)
            maxSize = allSeqs(j)._2 + 1
          }
        }
        allSeqs = allSeqs :+ (maxSeq, maxSize)
      }
      allSeqs.maxBy(_._2)
    }

    val (revSeq, size) = findLISAux(nums)
    (revSeq.reverse, size)
  }

  val lis  = findLIS(Seq(15, 27, 14, 38, 26, 55, 46, 65, 85))

  println(lis._2)
  println("-----------")

  lis._1 foreach println

}
