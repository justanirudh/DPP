package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 7/25/17.
 */
/*
300. Longest Increasing Subsequence
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        int[] maxLengths = new int[nums.length];
        maxLengths[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            maxLengths[i] = 1; //max yet
            for (int j = 0; j < i; ++j) {
                //if number greater than previous number AND resultant maxLength would be more
                if (nums[i] > nums[j] && maxLengths[j] + 1 > maxLengths[i])
                    maxLengths[i] = maxLengths[j] + 1;
            }
        }
        //going through maxLengths array and finding the largest value
        int maxLength = 1;
        for (int len : maxLengths) {
            if (len > maxLength)
                maxLength = len;
        }
        return maxLength;
    }
}
