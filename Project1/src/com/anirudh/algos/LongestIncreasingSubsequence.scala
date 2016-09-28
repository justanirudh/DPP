package com.anirudh.algos

/**
  * Created by anirudh on 25/9/16.
  */
object LongestIncreasingSubsequence extends App{

  def findLIS(nums:Seq[Int]):(Seq[Int], Int) = {
    var allSeqs = Seq[(Seq[Int], Int)](( Seq(nums.head), 1))

    for(i <- 1 until nums.size ){
      var maxSeq = Seq[Int]()
      var maxSize = 0
      for(j <- 0 until i){
        if(nums(j) < nums(i) && allSeqs(j)._2 + 1 > maxSize){
          maxSeq = allSeqs(j)._1 :+ nums(i)
          maxSize = allSeqs(j)._2 + 1
        }
      }
      allSeqs = allSeqs :+ (maxSeq, maxSize)
    }
    allSeqs.maxBy(_._2)
  }

  val lis  = findLIS(Seq(15, 27, 14, 38, 26, 55, 46, 65, 85))

  lis._1 foreach println

}
