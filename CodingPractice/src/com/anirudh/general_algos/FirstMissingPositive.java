package com.anirudh.general_algos;

/**
 * Created by paanir on 1/22/17.
 */
/*
41. First Missing Positive   Add to List QuestionEditorial Bloomberg_Chocolate_Fix  My Submissions
Total Accepted: 85982
Total Submissions: 344471
Difficulty: Hard
Contributors: Admin
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space

 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        //http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
        //http://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
        //first get rid of all negative numbers, throw all to left
        int j = 0;
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] <= 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        //now j is where the +ve array starts
        for(int i = j; i < nums.length; ++i){ //if number 5 exists, nums[4] would be negative; j is the offset
            if(Math.abs(nums[i]) - 1  + j < nums.length && nums[Math.abs(nums[i]) - 1 + j] > 0) //doing abs even though all +ve because we are changing them to negative
                nums[Math.abs(nums[i]) - 1 + j] = -nums[Math.abs(nums[i]) - 1 + j];
        }
        for(int i = j; i < nums.length; ++i){
            if(nums[i] > 0)
                return i + 1 - j; //+1 because if index y is neg, num y + 1 is present, - j because of offset
        }
        return nums.length + 1 - j; //since last i was nums.length - 1
    }
}
