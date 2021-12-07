package com.anirudh.companies_21_22.spotify;

import java.util.*;

/**
 * Created by paanir on 8/1/19.
 */
/*
15. 3Sum
Medium

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]


 */

/*
Sort the array
Use 3 pointers
1st pointer at target
2nd is the next elem and 3rd is the last elem
if 1 + 2 = -3, we have 1 sol
if 1+2 < -3, left++
if 1+2 > -3, right--
 */
public class ThreeSum {

    /*
    S: O(1)
    T: O(nlogn) + O(n^2)
     */

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums); //sort the array

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1]) //skip already visited pivot element
                continue;
            int target = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = leftNum + rightNum;
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], leftNum, rightNum)); //found one solution
                    while (left < nums.length && nums[left] == leftNum) {  //skip duplicates
                        left++;
                    }
                    while (right >= 0 && nums[right] == rightNum) {  //skip duplicates
                        right--;
                    }
                } else if (sum < target) {
                    while (left < nums.length && nums[left] == leftNum) {  //skip duplicates
                        left++;
                    }
                } else { //sum > target
                    while (right >= 0 && nums[right] == rightNum) {  //skip duplicates
                        right--;
                    }
                }
            }
        }

        return res;
    }

    /*
        Do TwoSum for each element (a + b = 0 - c)
        Time: O(N * N * 3log3) = O(N^2)
        Space: O(N)
     */
    public List<List<Integer>> threeSumMoreSpace(int[] nums) {
        Set<List<Integer>> sets = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int target = -1 * nums[i];
            map.clear(); //map from number to index
            for (int j = i + 1; j < nums.length; ++j) { //only use rest of the array for 2sum
                int num = nums[j];
                int complement = target - num;
                if (map.containsKey(complement)) {
                    //found a triplet
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(complement);
                    triplet.add(num);
                    Collections.sort(triplet);
                    sets.add(triplet);
                }
                map.put(num, j);
            }
        }
        return new ArrayList<>(sets);
    }
}
