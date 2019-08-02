package com.anirudh.subarray_substring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paanir on 7/22/19.
 */
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.


 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (numSet.contains(num - 1)) //some other smaller number will be the start of the sequence
                continue;
            else {
                int count = 0;
                int curr = num;
                while (numSet.contains(curr)) {
                    count++;
                    curr = curr + 1;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
