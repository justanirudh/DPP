package com.anirudh.dynamic_programming

/**
  * Created by anirudh on 23/10/16.
  */

object MaximumSumIncreasingSubsequence extends App{

  def findMSIS(weights:Seq[Int]):(Seq[Int], Int) = {

    //bottom-up approach
    def findMSISAux(weights:Seq[Int]):(Seq[Int], Int) = {
      //initializing 1st seq
      var allSeqs = Seq[(Seq[Int], Int)](( Seq(weights.head), weights.head))

      for(i <- 1 until weights.size ){ //starting from 2nd element of weights
      	
		//Initializing this to a sequence of element itself is important
      	//because if the below if statement is not true, it should atleast have its own value in its sequence
      	var longestSeq = Seq[Int](weights(i))
        
		// Initializing it to its current weight is important as it is used to compare.
        // Initializing this to 0 wont work
        var maxWeight = weights(i)
        
		for(j <- 0 until i){
          //comparing whether the weight is < the current weight && if we get a sum > the current max sum, we replace it
          if(weights(j) <= weights(i) && allSeqs(j)._2 + weights(i) > maxWeight){
            longestSeq = weights(i) +: allSeqs(j)._1 //so that prepending is O(1)
            maxWeight = allSeqs(j)._2  + weights(i)
          }
        }
        allSeqs = allSeqs :+ (longestSeq, maxWeight)
      }

      allSeqs.maxBy(_._2)
    }

    val (revSeq, size) = findMSISAux(weights)
    (revSeq.reverse, size)
  }

  //  val lis  = findMSIS(Seq(282, 15, 27, 14, 38, 26, 55, 46, 65, 85, 4))
  //val lis  = findMSIS(Seq(10,6,6)) //12
  val lis  = findMSIS(Seq(10,4,5,3)) //10
  println("Maximum weight is: " + lis._2)
  print("The sequence is: ")

  lis._1 foreach println

}

