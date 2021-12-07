package com.anirudh.companies_21_22.spotify;

/*
912. Sort an Array
Medium

1290

439

Add to List

Share
Given an array of integers nums, sort the array in ascending order.



Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 */
public class MergeSort {

    int[] nums;

    void merge(int start, int mid, int end) {
        int[] left = new int[mid - start + 1];
        int[] right = new int[end - (mid + 1) + 1];
        for (int i = start; i <= mid; ++i) {
            left[i - start] = nums[i];
        }
        for (int i = mid + 1; i <= end; ++i) {
            right[i - (mid + 1)] = nums[i];
        }
        //merge arrays
        int l = 0;
        int r = 0;
        int i = start;
        while (l < left.length || r < right.length) {

            int leftElem = l < left.length ? left[l] : Integer.MAX_VALUE;
            int rightElem = r < right.length ? right[r] : Integer.MAX_VALUE;

            if (leftElem < rightElem) {
                nums[i] = leftElem;
                l++;
            } else {
                nums[i] = rightElem;
                r++;
            }
            i++;
        }
    }

    public void mergeSort(int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        this.nums = nums;
        mergeSort(0, nums.length - 1);
        return nums;
    }
}
