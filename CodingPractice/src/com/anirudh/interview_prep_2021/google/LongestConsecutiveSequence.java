package com.anirudh.interview_prep_2021.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paanir on 7/22/19.
 */
/*
128. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
/*
Put all in hashset. Then find the STARTING element of each such sequence
1. put them all in a hashset
2. got through array elems
2.1 if (elem - 1) is in hashset, DO NOTHING. As the elem-1 elem might start the sequence
2.2 if (elem - 1) is NOT in hashset; start count = 0, keep count++ until elem++ in hashset. compare with max
3. return max
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
