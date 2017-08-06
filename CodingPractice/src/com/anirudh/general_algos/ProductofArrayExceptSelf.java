package com.anirudh.general_algos;

/**
 * Created by paanir on 8/5/17.
 */
/*
238. Product of Array Except Self
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of
all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra
space for the purpose of space complexity analysis.)
 */
public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        int tmp = 1;
        for(int i = 0; i < nums.length; ++i){
            res[i] = tmp; //no * as compared to below loop
            tmp *= nums[i];
        }
        //res[i] has product of all numbers to its left

        //now multiplying existing values with values to the right of each number
        tmp = 1;
        for(int i = nums.length - 1; i >= 0; --i){
            res[i] *= tmp;
            tmp *= nums[i];
        }
        return res;
    }
}
