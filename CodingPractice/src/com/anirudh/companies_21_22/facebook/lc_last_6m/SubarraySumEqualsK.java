package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/27/21.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * Medium
 * 
 * 8722
 * 
 * 292
 * 
 * Add to List
 * 
 * Share
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * 
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

/**
 * Use runningSum and complement
 * Create a MAP{runningSum -> number of times it has appeared}
 * For every runningSum, check if {runningSum-k} is in map. if it is, add it to the final result
 * Populate hashMap with {runningSum -> number of times it has appeared}
 *
 * Catch: For every loop, update final result before updating map. For k = 0, if we update map before updating result,
 * every runningSum will be correct answer as runningSum-0 = runningSum
 */

public class SubarraySumEqualsK {
    //O(n) time, O(n) space
    public int subarraySum(int[] nums, int k) {
        //Map from runningSum to how many times it has appeared until now
        Map<Integer, Integer> freq = new HashMap<>();
        int res = 0;
        int runningSum = 0;
        freq.put(0, 1); //0 has come once; required if k is the runningSum at some point
        for (int num : nums) {
            runningSum += num;
            // if complement of the runningSum appears, count would be the number of times it has appeared until now
            if (freq.containsKey(runningSum - k)) {
                res += freq.get(runningSum - k);
            }
            freq.put(runningSum, freq.getOrDefault(runningSum, 0) + 1);
        }
        return res;
    }
}
