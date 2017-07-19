package com.anirudh.general_algos;

/**
 * Created by paanir on 7/18/17.
 */
/*
27. Remove Element
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElementInplace {

    public int removeElement(int[] nums, int val) {
        int lastAccepted = nums.length - 1;
        int count = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] == val) {
                //swap
                int temp = nums[lastAccepted];
                nums[lastAccepted] = nums[i];
                nums[i] = temp;
                //decrement next to be swapped value's index
                lastAccepted--;
                //increment number of times we swapped
                count++;
            }
        }
        return nums.length - count; //non val elems in total - num of swaps
    }

}
