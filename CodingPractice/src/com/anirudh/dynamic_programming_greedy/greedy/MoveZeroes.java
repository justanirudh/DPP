package com.anirudh.dynamic_programming_greedy.greedy;

/**
 * Created by paanir on 10/5/17.
 */
/*
283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while (true) {
            //right pointer helps find all non-zero elements and together with left, accumulate it in the start
            while (right != nums.length && nums[right] == 0)
                right++;
            if (right == nums.length)
                break;
            nums[left] = nums[right];
            left++;
            right++;
        }
        while (left != nums.length) {
            nums[left] = 0;
            left++;
        }
        return;
    }
}
