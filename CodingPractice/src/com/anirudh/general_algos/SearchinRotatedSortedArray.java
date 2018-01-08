package com.anirudh.general_algos;

/**
 * Created by paanir on 1/7/18.
 */
/*
33. Search in Rotated Sorted Array
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */
//Always check the increasing side
public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            //check the increasing side
            if (nums[left] <= nums[mid]) { //left side is increasing side
                if (target >= nums[left] && target < nums[mid]) { //if target on left side
                    right = mid - 1; //or normal binary search
                } else { //target on right side
                    left = mid + 1;
                }
            } else { //right side is increasing side
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
