package com.anirudh.interview_prep_2021.two_sigma;

import java.util.HashSet;
import java.util.Set;

/*
349. Intersection of Two Arrays
Easy

1859

1719

Add to List

Share
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
 */
/*
put both array in sets. do set1.retainAll(set2)
 */
public class IntersectionofTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> n1Set = new HashSet<>();
        for(int num1 : nums1) {
            n1Set.add(num1);
        }
        Set<Integer> n2Set = new HashSet<>();
        for(int num2 : nums2) {
            n2Set.add(num2);
        }
        n1Set.retainAll(n2Set);
        int[] res = new int[n1Set.size()];
        int i = 0;
        for(int n : n1Set) {
            res[i] = n;
            i++;
        }
        return res;
    }
}
