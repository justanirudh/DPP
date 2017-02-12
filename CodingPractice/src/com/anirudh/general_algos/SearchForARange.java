package com.anirudh.general_algos;

/**
 * Created by paanir on 12/31/16.
 */
/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */
public class SearchForARange {

    public static int getIndex(int[] nums, int target, int start, int end) {
        if (end < start)
            return -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] > target)
            return getIndex(nums, target, start, mid - 1);
        else
            return getIndex(nums, target, mid + 1, end);

    }

    public static int[] searchRange(int[] nums, int target) {
        int index = getIndex(nums, target, 0, nums.length - 1);
        if (index == -1) {
            int arr[] = {-1, -1};
            return arr;
        }
        int left = index;
        while (left >= 0 && nums[left] == target)
            left--;
        left = left + 1;

        int right = index;
        while (right < nums.length && nums[right] == target)
            right++;
        right = right - 1;

        int arr[] = {left, right};
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] ans = searchRange(nums, target);
        for (Integer i : ans)
            System.out.println(i);
    }
}
