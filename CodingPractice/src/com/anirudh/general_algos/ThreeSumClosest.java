package com.anirudh.general_algos;

import java.util.Arrays;

/**
 * Created by paanir on 3/20/17.
 */
/*
16. 3Sum Closest
Given an array S of n integers, find three integers in S such that the sum is closest to a
given number, target. Return the sum of the three integers. You may assume that each input
would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosestJ {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length == 0 || nums.length == 1 || nums.length == 2)
            return -1;
        int sum = 0;
        int mindiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; ++i) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                System.out.println(i + "," + j + "," + k);
                int currSum = nums[i] + nums[j] + nums[k];
                if (currSum == target)
                    return target;
                int diff = Math.abs(currSum - target);
                if (diff < mindiff) {
                    mindiff = diff;
                    sum = currSum;
                }
                if (currSum > target)
                    k--;
                else
                    j++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{1, 1, 1, 0}, 100));
    }
}
