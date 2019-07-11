package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 8/17/17.
 */
/*
213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will
not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house
is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
 */
public class HouseRobber2 {

    int maxRob(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        if (start + 1 <= end)
            dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        else
            return dp[start];
        for (int i = start + 2; i <= end; ++i) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[end];
    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int max1 = maxRob(nums, 0, nums.length - 2); //skip last elem
        int max2 = maxRob(nums, 1, nums.length - 1); //skip first elem
        return Math.max(max1, max2);
    }
}
