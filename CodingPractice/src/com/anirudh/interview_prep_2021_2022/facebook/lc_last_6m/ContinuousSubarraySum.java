package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m;

/**
 * Created by paanir on 8/28/21.
 */
/*
523. Continuous Subarray Sum
Medium

668

113

Add to List

Share
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two
whose elements sum up to a multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.


Example 1:

Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
Example 2:

Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
Example 3:

Input: nums = [23,2,6,4,7], k = 13
Output: false
 */

import java.util.HashMap;
import java.util.Map;

/**
 * IMPORTANT
 * https://leetcode.com/problems/continuous-subarray-sum/discuss/99499/Java-O(n)-time-O(k)-space
 * <p>
 * Calculate running sum.
 * Create a mapping of  { Remainders -> Index }
 * For each sum SUM, store (SUM mod k) -> Index
 * If you see a remainder again, take the difference of the indices
 * if the difference >1 (as question asks us to have size of subarray >1), return true
 * <p>
 * (x + n*k) % k = x % k
 */
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> rems = new HashMap<>();
        rems.put(0, -1); //runningSum is 0 and its index is -1. required if the entire array's sum is a multiple of k
        int runningSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            runningSum += nums[i];
            int rem = runningSum % k;
            if (rems.containsKey(rem)) { //seen this remainder before
                if (i - rems.get(rem) >= 2)  // i - rems.get(rem) will give me the size of the array as the array itself is from [rems.get(rem) + 1, i]
                    return true;
            } else {
                rems.put(rem, i);
            }
        }
        return false;
    }
}
