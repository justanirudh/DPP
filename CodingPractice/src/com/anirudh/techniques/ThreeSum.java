package com.anirudh.techniques;

import java.util.*;

/**
 * Created by paanir on 8/1/19.
 */
/*
15. 3Sum
Medium

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]


 */
public class ThreeSum {

    /*
    For O(1) space, sort, choose ith elem and use 2-pointer approach for i+1 to len-1 elements
     */

    /*
        Do TwoSum for each element (a + b = 0 - c)
        Time: O(N * N * 3log3) = O(N^2)
        Space: O(N)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> sets = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int target = -1 * nums[i];
            map.clear(); //map from number to index
            for (int j = i + 1; j < nums.length; ++j) { //only use rest of the array for 2sum
                int num = nums[j];
                int complement = target - num;
                if (map.containsKey(complement)) {
                    //found a triplet
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(complement);
                    triplet.add(num);
                    Collections.sort(triplet);
                    sets.add(triplet);
                }
                map.put(num, j);
            }
        }
        return new ArrayList<>(sets);
    }
}
