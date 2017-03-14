package com.anirudh.general_algos;

import java.util.HashMap;

/**
 * Created by paanir on 3/13/17.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (Integer i : nums)
            hm.put(i, target - i);
        for (Integer key : hm.keySet()) {
            int val = hm.get(key);
            if (hm.containsKey(val)) {
                int[] arr = {key, val};
                return arr;
            }
        }
        return new int[0];
    }
}
