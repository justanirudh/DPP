package com.anirudh.companies_21_22.facebook.lc_last_6m;

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

/**
 * On first pass from l to r, at index i, save product of elements 0 to i-1
 * On second pass from right to left, calculate rightProduct and multiple with the above array
 *
 * [i,j,k,l,m,n] on the 2nd pass, 1st array[l] will have multi of all values to left
 */
public class ProductofArrayExceptSelf {
    static int[] productExceptSelf(int[] nums) {

        //O(1) space, O(n) time [only results array produced, no other space used]
        int[] res = new int[nums.length];

        //res[i] has product of all numbers strictly to its left
        res[0] = 1; //on the left of first elem is 1
        for (int i = 1; i < nums.length; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        //now multiplying existing values with values to the right of each number
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            res[i] = res[i] * rightProduct; //leftproduct * rightproduct
            rightProduct = rightProduct * nums[i]; //now calculate product on the right
        }

        return res;


        //------------------------------------------------------------------------
    }

    static int[] productExceptSelf2(int[] nums) {
        //O(n) space, O(n) time
        int[] left = new int[nums.length]; //left to right cumulative products
        int[] right = new int[nums.length]; //right to left cumulative products

        left[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            left[i] = left[i - 1] * nums[i];
        }

        right[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; --i) {
            right[i] = right[i + 1] * nums[i];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int leftProd = (i - 1 >= 0) ? left[i - 1] : 1;
            int rightProd = (i + 1 < nums.length) ? right[i + 1] : 1;
            res[i] = leftProd * rightProd;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] in = {5, 8, 10, 12};
        int[] res = productExceptSelf(in);
    }
}
