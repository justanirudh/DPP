package com.anirudh.general_algos;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by paanir on 9/20/17.
 */
/*
215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
//Good solutions: https://leetcode.com/problems/kth-largest-element-in-an-array/description/
public class KthLargestElementInAnArray {

    //O(nlogn) T, O(1) S
    public int findKthLargest(int[] nums, int k) {
         Arrays.sort(nums);
         return nums[nums.length-1 - (k-1)];
    }

    //O(nlogk) T, O(k) S
    public int findKthLargest2(int[] nums, int k) {
        //k size min heap
        final PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int val : nums) {
            pq.offer(val);

            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    //Best approach: O(n) T, O(1) S ON AN AVERAGE: Randomized Quick Select (worst case still O(n^2))
}
