package com.anirudh.general_algos.find_subarray;

import java.util.Arrays;

/**
 * Created by paanir on 10/8/17.
 */
/*
581. Shortest Unsorted Continuous Subarray
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
 */
//O(logn) time O(n) space
    //better solution (On and On) suing stack!!!(https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/)
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums,0, copy, 0, nums.length);
        Arrays.sort(copy);
        int start = 0, end = 0;
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != copy[i]){
                start = i;
                break;
            }
        }
        for(int i = nums.length - 1; i >= 0; --i){
            if(nums[i] != copy[i]){
                end = i;
                break;
            }
        }
        if(start == 0 && end == 0)
            return 0;
        else
            return end - start + 1;

    }
}
