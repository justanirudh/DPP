package com.anirudh.general_algos;

/**
 * Created by paanir on 1/12/17.
 */
/*
268. Missing Number   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 86619
Total Submissions: 198856
Difficulty: Medium
Contributors: Admin
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {

    //O(n) T, O(n) S
    public int missingNumberNaive(int[] nums) {
        boolean[] present = new boolean[nums.length + 1]; //all false
        for(int i = 0; i < nums.length; ++i){
            present[nums[i]] = true;
        }
        for(int i = 0; i < present.length; ++i){
            if(!present[i])
                return i;
        }
        return -1;
    }

    //O(n) T O(1) space
    public int missingNumber(int[] nums) {
        int maxNum = nums.length;
        int expectedSum = maxNum * (maxNum + 1)/2; //sum of all nums
        int actualSum = 0;
        for(int i = 0; i < nums.length; ++i)
            actualSum += nums[i];
        return expectedSum - actualSum; //this will give the missing num
    }

}
