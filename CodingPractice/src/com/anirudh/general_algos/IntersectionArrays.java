package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by paanir on 2/4/17.
 */
/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

 */
public class IntersectionArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < nums1.length; ++i) {
            int curr = nums1[i];
            if (!map.containsKey(curr))
                map.put(curr, true);
        }

        ArrayList<Integer> intersect = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i])) {
                intersect.add(nums2[i]);
                map.put(nums2[i], false);
            }
        }
        Integer[] temp = intersect.toArray(new Integer[0]);
        int[] arr = new int[temp.length];
        for (int i = 0; i < temp.length; ++i) {
            arr[i] = temp[i].intValue();
        }
        return arr;
    }
}
