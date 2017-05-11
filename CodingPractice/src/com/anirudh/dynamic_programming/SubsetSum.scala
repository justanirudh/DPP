package com.anirudh.dynamic_programming

/**
  * Created by anirudh on 23/11/16.
  */
/*
Dynamic Programming | Set 25 (Subset Sum Problem)
Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal
to given sum.

Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4, 5) with sum 9.

NP complete: pseudo polynomial, input sensitive as O(sum * n)
 */
object SubsetSum extends App{

  def isSubsetSum(nums:Seq[Int], sum:Int):Boolean = {

    //sum + 1 rows and index + 1 columns; plus 1 in both for defining base cases
    // At ith row meaning currently evaluating if sum was i
    //At jth column meaning currently evaluating if nums was first j-1 elements of num
    val matrix = Array.ofDim[Boolean](sum + 1, nums.size + 1)

    //if sum is 0, for all values of num, return true
    for(firstjnums <- 0 to nums.size){
      matrix(0)(firstjnums) = true
    }

    //if first 0 nums taken from array 'nums', for all sums >= 1, return 0, as size of set is 0
    for(sum <- 1 to sum){
      matrix(sum)(0) = false
    }

    for(sum <- 1 to sum){
      for(firstjnums <- 1 to nums.size){

        //first just equate to not taking the current element. Because if value for previous element was true,
        //It is going to be true for all the ones following it. Eg. if sum = 9 is a subsetsum of {1, 4, 5}, it is also
        //going to be subsetsum of {1,4,5,6,7,3,.....}
        matrix(sum)(firstjnums) = matrix(sum)(firstjnums - 1)
        if(!matrix(sum)(firstjnums)){ //if it is not true, only then check for next
          if(sum >= nums(firstjnums - 1)){ // a subset sum might be ending at this column
            //going to row sum - currentnum as checking if their is a subset excluding the current element(hence firstjnums - 1)
            // that has the sum as the targetted sum (current row's index) - current num
            matrix(sum)(firstjnums) = matrix(sum - nums(firstjnums - 1))(firstjnums - 1)
          }
          //else if sum (current row number) < the corresponding element in nums array, no point in checking.
        }
        //else continue for the rest of the row, all columns of this row after a 'true' will be 'true'
      }
    }

    matrix(sum)(nums.size)
  }

  /*
  sum
  0 t
  1 f
  2 t
  3 t
  4 t
  5 t
  6 t
  7 t
  8 t
  9 t
  10 t
  11 t
  12 t
  13 f
   */
  val nums = Seq(3, 34, 4, 12, 5, 2)
  val sum = 10
  val isSubSum = isSubsetSum(nums, sum)
  println(isSubSum)

}
