package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 7/19/17.
 */
/*
53. Maximum Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

    //DP, O(n) solution
    public int maxSubArray(int[] a) {
        //http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
        /*
        Kadane's algo

        Initialize:
        max_so_far = 0
        max_ending_here = 0

        Loop for each element of the array
        (a) max_ending_here = max_ending_here + a[i]
        (b) if(max_ending_here < 0)
            max_ending_here = 0
        (c) if(max_so_far < max_ending_here)
            max_so_far = max_ending_here
        return max_so_far

         */
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE;
        int max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

    //------------------------------------------
    public int maxSubArrayNaive(int[] nums) {
        //brute-force, O(n^2) solution
        //TLE

        int[][] mat = new int[nums.length][nums.length];
        for (int i = 0; i < mat.length; ++i)
            mat[i][0] = nums[i];

        int maxSum = mat[0][0];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 1; j < mat[0].length; ++j) {
                if (i >= j)
                    mat[i][j] = mat[i][j - 1];
                else
                    mat[i][j] = mat[i][j - 1] + nums[j];
                if (mat[i][j] > maxSum)
                    maxSum = mat[i][j];
            }
        }
        return maxSum;
    }

}
