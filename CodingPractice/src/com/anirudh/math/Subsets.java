package com.anirudh.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 6/7/17.
 */
/*
78. Subsets

Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
/*
Idea is to go through all numbers from 0 until 2^nums.length
for each of those numbers, its bit representation would give us the subset
if the bit representation is 1 at a place, include the corresponding number in nums array, else dont
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        int numSets = (int) Math.pow(2, nums.length);
        List<List<Integer>> res = new ArrayList<>();

        for (int bitmap = 0; bitmap < numSets; ++bitmap) { //bitmap = arrangement
            List<Integer> subset = new ArrayList<>();
            //populate list

            for (int i = 0; i < nums.length; ++i) {
                int include = ((bitmap >> i) & 1); //bit at given bit index
                if (include == 1)
                    subset.add(nums[i]);
            }
            res.add(subset);
        }
        return res;
    }
}
