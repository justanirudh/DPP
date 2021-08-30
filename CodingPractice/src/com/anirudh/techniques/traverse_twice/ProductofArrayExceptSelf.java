package com.anirudh.techniques.traverse_twice;

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

//O(n) time, O(1) space [only results array produced, no other space used]
public class ProductofArrayExceptSelf {
    static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        //res[i] has product of all numbers to its left
        res[0] = 1; //on the left of first elem is 1
        for (int i = 1; i < nums.length; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        //now multiplying existing values with values to the right of each number
        int prod = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            res[i] *= prod;
            prod *= nums[i]; //now calculate product on the right
        }

        return res;
    }

    public static void main(String[] args) {
        int[] in = {5, 8, 10, 12};
        int[] res = productExceptSelf(in);
    }
}
