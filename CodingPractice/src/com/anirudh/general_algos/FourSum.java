package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by paanir on 5/30/17.
 */
/*
18. 4Sum
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */
public class FourSum {
    //O(nlogn) + O(n^3) = O(n^3) time, O(1) space
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        // List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> lists = new HashSet<>();
        for (int i = 0; i < nums.length - 3; ++i) {
            for (int j = i + 1; j < nums.length - 2; ++j) {
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        lists.add(list);
                        k++;
                        l--;
                    } else if (sum < target)
                        k++;
                    else
                        l--;
                }
            }
        }
        return new ArrayList<>(lists);
    }
}
