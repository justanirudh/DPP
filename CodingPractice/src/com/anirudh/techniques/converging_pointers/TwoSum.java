package com.anirudh.techniques.converging_pointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 3/13/17.
 */

public class TwoSum {
    /*
1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

    //O(1) space, O(n) time
    public int[] twoSum(int[] nums, int target) { // 1 pass
        Map<Integer, Integer> sums = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int complement = target - num;
            if (sums.containsKey(complement)) {//if complement is already a key to map
                return new int[]{sums.get(complement), i};
            }
            sums.put(num, i);
        }
        return new int[0];
    }

    /*
    * 167. Two Sum II - Input array is sorted
    Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
    Please note that your returned answers (both index1 and index2) are not zero-based.

    You may assume that each input would have exactly one solution and you may not use the same element twice.

    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=1, index2=2
    * */
    //If input sorted

    public int[] twoSumSortedInput(int[] numbers, int target) {
        if (numbers.length == 0)
            return new int[0];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; //+1 because arrays are not zero-based :-|
            } else if (sum < target)
                left++;
            else
                right--;
        }
        return new int[0];
    }

}
