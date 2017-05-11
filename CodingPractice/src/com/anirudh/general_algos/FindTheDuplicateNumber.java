package com.anirudh.general_algos;

/**
 * Created by paanir on 1/20/17.
 */
/*
287. Find the Duplicate Number   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 51769
Total Submissions: 123698
Difficulty: Hard
Contributors: Admin
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
Note:
You must not modify the array (assume the array is read only).

>>You must use only constant, O(1) extra space.
>>Your runtime complexity should be less than O(n2).

There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {

    //For every number from 1 to n, traversing the array (of length n + 1) and counting its appearances
    //Hence T = O(n^2) and S = O(1)
    public int findDuplicate(int[] nums) {
        for(int num = 1; num < nums.length; ++num){
            int count = 0;
            for(int i = 0; i < nums.length; ++i){
                if(nums[i] == num)
                    count++;
                if(count == 2)
                    return num;
            }
        }
        return -1;
    }
}
