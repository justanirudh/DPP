package com.anirudh.general_algos;

import java.util.HashMap;

/**
 * Created by paanir on 5/24/17.
 */
/*
137. Single Number II
Given an array of integers, every element appears three times except for one, which appears exactly once.
Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber2 {

    //O(n) space, O(n) time
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1)
                return key;
        }
        return -1;
    }

    //bitwise soln on leetcode (O(1) space, O(n) time)
}
