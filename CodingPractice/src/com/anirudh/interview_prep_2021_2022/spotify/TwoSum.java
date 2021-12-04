package com.anirudh.interview_prep_2021_2022.spotify;

import java.util.HashMap;
import java.util.Map;

/*
1. Two Sum
Easy

24373

807

Add to List

Share
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 */

/*
create map: value -> index
For each new value, check if target-value is already present in graph or not
if yes, return both indices
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int complement = target - nums[i];
            if (valueToIndex.containsKey(complement)) {
                int[] res = new int[2];
                res[0] = valueToIndex.get(complement);
                res[1] = i;
                return res;
            } else {
                valueToIndex.put(nums[i], i);
            }
        }
        return null;
    }

}
