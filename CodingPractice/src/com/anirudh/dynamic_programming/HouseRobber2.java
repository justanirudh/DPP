package com.anirudh.dynamic_programming;

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

    int maxRob(int[] nums, int start, int end){
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = (int)Math.max(nums[start], nums[start + 1]);
        for(int i = 2; i <= end; ++i){
            dp[start + i] = (int)Math.max(nums[]);
        }
    }


    public int rob(int[] nums) {
        int max1 = maxRob(nums, 0, nums.length - 2);
        int max2 = maxRob(nums, 1, nums.length - 1);
        return (int)Math.max(max1, max2);
    }
}
