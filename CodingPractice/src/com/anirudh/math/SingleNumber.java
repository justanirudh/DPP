package com.anirudh.math;

/**
 * Created by paanir on 6/4/17.
 */
/*
136. Single Number

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {
    //every number appears twice except one
    //duplicate numbers will cancel each other when XORed with each other
    //only the single number will remain as the answer
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
