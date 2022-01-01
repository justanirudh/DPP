package com.anirudh.companies_21_22.google.lc_last_6m.anki;

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
/*
recurrence relation is: dp[i] = max(dp[j] + 1) for all j where nums[j] < nums[i] and j < i.
Tx: O(n^2)

 */
public class DP_LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        int[] maxLengthAt = new int[nums.length];
        maxLengthAt[0] = 1;

        int maxLength = 1;
        for (int i = 1; i < nums.length; ++i) {
            maxLengthAt[i] = 1; //max yet
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) //if number greater than previous number AND resultant maxLength would be more
                    maxLengthAt[i] = Math.max(maxLengthAt[i], maxLengthAt[j] + 1);
            }
            if (maxLengthAt[i] > maxLength) //going through maxLengths array and finding the largest value
                maxLength = maxLengthAt[i];
        }
        return maxLength;
    }
}
