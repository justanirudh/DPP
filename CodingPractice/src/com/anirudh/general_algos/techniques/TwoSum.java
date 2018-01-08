package com.anirudh.general_algos.techniques;

import java.util.HashMap;

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

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (Integer i : nums)
            hm.put(i, target - i);
        for (Integer key : hm.keySet()) {
            int val = hm.get(key);
            if (hm.containsKey(val)) {
                int first = 0;
                int second = 0;
                for (int i = 0; i < nums.length; ++i) {
                    if (nums[i] == key) {
                        first = i;
                        break;
                    }
                }
                for (int i = 0; i < nums.length; ++i) {
                    if (nums[i] == val && i != first) {
                        second = i;
                        break;
                    }
                }
                int[] arr = {first, second};
                return arr;
            }
        }
        return new int[0];
    }

    /*
    * 167. Two Sum II - Input array is sorted
    Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

    You may assume that each input would have exactly one solution and you may not use the same element twice.

    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=1, index2=2
    * */
    //If input sorted

    public int[] twoSumSortedInput(int[] numbers, int target) {
        if (numbers.length == 0)
            return new int[0];
        int front = 0;
        int back = numbers.length - 1;
        while (front < back) {
            int sum = numbers[front] + numbers[back];
            if (sum == target) {
                return new int[]{front + 1, back + 1};
            } else if (sum < target)
                front++;
            else
                back--;
        }
        return new int[0];
    }

}
