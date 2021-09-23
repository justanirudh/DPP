package com.anirudh.interview_prep_2021.two_sigma.anki;

import java.util.ArrayList;
import java.util.List;

/*
78. Subsets
Medium

7057

124

Add to List

Share
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 */
/*
    Use bitmask. Go from 2^n to 2^(n+1) and ignore first bit to get bitmask of fixed n length
 */
public class Subsets {
    //TODO
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (double i = Math.pow(2, len); i < Math.pow(2, len + 1); ++i) {
            String bitmask = Integer.toBinaryString((int) i).substring(1);
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < bitmask.length(); ++j) {
                if (bitmask.charAt(j) == '1')
                    curr.add(nums[j]);
            }
            res.add(curr);
        }
        return res;
    }
}
