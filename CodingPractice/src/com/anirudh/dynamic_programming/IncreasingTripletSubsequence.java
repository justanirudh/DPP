package com.anirudh.dynamic_programming;

import java.util.ArrayList;

/**
 * Created by paanir on 1/7/17.
 */
/*
334. Increasing Triplet Subsequence   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 30855
Total Submissions: 81747
Difficulty: Medium
Contributors: Admin
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.
Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
 */
public class IncreasingTripletSubsequence {
    //equal to LIS
    //This is O(n^2). O(n) in leetcode

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3)
            return false;
        ArrayList<Integer> maxEnds = new ArrayList<>();
        maxEnds.add(1); //first elem
        for (int i = 1; i < nums.length; ++i) {
            int currMaxEnd = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i] && maxEnds.get(j) + 1 > currMaxEnd)
                    currMaxEnd = maxEnds.get(j) + 1;
            }
            maxEnds.add(i, currMaxEnd); //here only check if maxEnd >=3, that will work faster
        }
        int maxEnd = maxEnds.get(0);
        for (int i = 0; i < maxEnds.size(); ++i) {
            if (maxEnds.get(i) > maxEnd)
                maxEnd = maxEnds.get(i);
        }
        return maxEnd >= 3;
    }

    public static void main(String[] args) {
        int[] nums = {15, 27, 14, 38, 26, 55, 46, 65, 85};
        System.out.println(increasingTriplet(nums));
    }
}
