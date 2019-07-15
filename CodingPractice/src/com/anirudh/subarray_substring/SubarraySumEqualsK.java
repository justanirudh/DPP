package com.anirudh.subarray_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 7/14/19.
 */
/*
560. Subarray Sum Equals K
Medium

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:

    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        /*
        1. create a map of runningSums of all numbers in nums array
        2. Before adding the running sum, check if the (running_sum - k) is already in the map. If it is, add the value corresponding
        to the old_running_sum to the result
        3. add the current_running_sum to the map

        Explaination of 2.
        at index i, running_sum = RS1
        at index j (s.t. j > i), running_sum = RS2
        If RS2 - RS1 = k, this means the array between indices i and j sums to k
        And we add the value coorresponding to RS1 key to the result, because that is the number of times the running_sum has been RS1
        So, for all those counts (indices i1, i2, i3), the subarrays (i1 to j, i2 to j, i3 to j) have sum k
         */
        Map<Integer, Integer> sums = new HashMap<>();
        int count = 0;
        int runningSum = 0;
        sums.put(0, 1); //0 appears once

        for (int num : nums) {
            runningSum += num;

            if (sums.containsKey(runningSum - k)) { //check if a sum_old exists such that sum_new - sum_old = k (means sum_old = sum_new - k)
                count += sums.get(runningSum - k);
            }

            if (!sums.containsKey(runningSum)) //add running sum to map
                sums.put(runningSum, 0);
            sums.put(runningSum, sums.get(runningSum) + 1);

        }
        return count;
    }
}
