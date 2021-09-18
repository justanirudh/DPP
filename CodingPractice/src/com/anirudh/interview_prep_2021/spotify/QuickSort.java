package com.anirudh.interview_prep_2021.spotify;

/*
Given an array of integers nums, sort the array in ascending order.

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 */
public class QuickSort {
    int[] nums;

    int getParition(int start, int end) {
        int pivot = nums[start];
        int left = start + 1;
        for (int right = start + 1; right <= end; right++) {
            if (nums[right] < pivot) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
            }
        }
        left--; //go back to the last <pivot elem
        int tmp = nums[left];
        nums[left] = pivot;
        nums[start] = tmp;
        return left;
    }

    void quickSort(int start, int end) {
        if (start < end) {
            int pivotIdx = getParition(start, end);
            quickSort(start, pivotIdx - 1);
            quickSort(pivotIdx + 1, end);
        }
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        this.nums = nums;
        quickSort(0, nums.length - 1);
        return nums;
    }
}
