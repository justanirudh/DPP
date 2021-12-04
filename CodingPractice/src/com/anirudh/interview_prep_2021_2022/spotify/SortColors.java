package com.anirudh.interview_prep_2021_2022.spotify;

/*
75. Sort Colors
Medium

6717

339

Add to List

Share
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are
adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]
 */

/*
Think of QuickSort
Keep 2 pointers: p0 for 0s and p2 for 2s
initialize p0 at index 0 and p2 at numLen - 1
If you see a 0, swap with elem at p0; if you see a 2, swap with elem at p2
CATCH: when an elem is swapped with 2 when num[curr] == 2, DO NOT increment curr as the elem still needs to be processed in next iteration
curr can be incremented safely for 0 swap as 0 is swapped with elems from left, meaning they have already been processed!
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int i = 0; //store 0s
        int j = nums.length - 1; //store 2s
        for(int curr = 0; curr <= j; ++curr) {
            if(nums[curr] == 0) {
                nums[curr] = nums[i]; //swap with nums[i]
                nums[i] = 0;
                i++;
            }
            else if (nums[curr] == 2) {
                nums[curr] = nums[j];
                nums[j] = 2;
                j--;
                curr--; //decrement curr here so that in next iteration the elem that got swapped will be processed
            }
            //else if it is 1, NOOP
        }
    }
}
