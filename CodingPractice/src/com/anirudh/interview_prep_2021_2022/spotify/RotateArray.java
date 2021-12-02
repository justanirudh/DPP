package com.anirudh.interview_prep_2021_2022.spotify;

/*189. Rotate Array
Medium

5830

1012

Add to List

Share
Given an array, rotate the array to the right by k steps, where k is non-negative.



Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */

/*
1. rotate whole array
2. rotate first k elems
3. rotate last n-k elems
 */
public class RotateArray {

    int[] nums;

    void rotateArray(int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        this.nums = nums;
        int len = nums.length;
        k = k % len;
        rotateArray(0, len - 1);
        rotateArray(0, k - 1);
        rotateArray(k, len - 1);
    }
}
