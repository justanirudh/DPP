package com.anirudh.subarray_substring;

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

    //Greedy, O(n) solution
    //https://leetcode.com/problems/maximum-subarray/solution/
        /*
        to find the subarray
        record when each potential array starts (choose nums[i] for maxEndingHere) until next nums[i]
        find the overall max position in that array, this is the end
         find the nums[i] chosen as we go left from this index, this is the start
         return {start index, end index}
         */
    public static int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

//        int maxEndingHere = nums[0], max = nums[0];

//        for (int i = 1; i < nums.length; ++i) {
//            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]); //either start a new subarray or continue from prev
//            max = Math.max(maxEndingHere, max); //finding highest maxEndingHere
//        }

        int runningSum = nums[0], max = nums[0];
        int start = 0, end = 0, s = 0;
        for (int i = 1; i < nums.length; ++i) {
            runningSum += nums[i];

            if (nums[i] > runningSum) { //starting a new array
                runningSum = nums[i];
                s = i;
            }

            if (runningSum > max) { //found a max
                max = runningSum;
                start = s;
                end = i;

            }
        }

        //actual subarray
        for(int i = start; i <= end; ++i){
            System.out.print(nums[i] + ",");
        }

        return max;

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

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println( "\nMax sum is " + maxSubArray(arr));
    }

}
