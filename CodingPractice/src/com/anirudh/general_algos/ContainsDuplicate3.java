package com.anirudh.general_algos;

/**
 * Created by paanir on 5/17/17.
 */
/*
220. Contains Duplicate III
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute
difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 */

//#220 question. SUB-OPTIMAL SOLUTION
public class ContainsDuplicate3 {

    //O(n^2) time, O(1) space
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                double diffNums = Math.abs((double)nums[i] - (double)nums[j]);
                System.out.println(diffNums);
                double diffIx = (double) Math.abs(i - j);
                System.out.println(diffIx);
                if (diffNums <= t && diffIx <= k)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate3 cd = new ContainsDuplicate3();
        boolean ret = cd.containsNearbyAlmostDuplicate(new int[]{-1, 2147483647}, 1, 2147483647);
        System.out.println(ret);
    }
}
