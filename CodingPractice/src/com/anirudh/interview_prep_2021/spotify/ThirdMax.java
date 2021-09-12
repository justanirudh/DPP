package com.anirudh.interview_prep_2021.spotify;

import java.util.*;

/**
 * Created by paanir on 3/29/17.
 */
/*
414. Third Maximum Number
Easy

1258

1997

Add to List

Share
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.



Example 1:

Input: nums = [3,2,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.
Example 2:

Input: nums = [1,2]
Output: 2
Explanation:
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.
Example 3:

Input: nums = [2,2,3,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2's are counted together since they have the same value).
The third distinct maximum is 1.
 */
    /*
    Put in 3 size heap

     */
public class ThirdMax {

    public int thirdMax(int[] nums) { //can input the rank of the number
        if(nums.length == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (set.size() == 1) {
            List<Integer> arr = new ArrayList<>(set);
            return arr.get(0);
        }
        else if(set.size() == 2) {
            List<Integer> arr = new ArrayList<>(set);
            return arr.get(0) > arr.get(1) ? arr.get(0) : arr.get(1);
        }
        else {
            PriorityQueue<Integer> q = new PriorityQueue<>(); //min heap
            for (int num : set) {
                q.offer(num);
                if (q.size() > 3)
                    q.poll();
            }
            return q.peek();
        }

    }
}
