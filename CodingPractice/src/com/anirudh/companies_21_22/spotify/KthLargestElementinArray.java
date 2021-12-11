package com.anirudh.companies_21_22.spotify;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 215. Kth Largest Element in an Array
 * Medium
 * 
 * 6627
 * 
 * 396
 * 
 * Add to List
 * 
 * Share
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * 
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */

//O(nlogk) time, O(k) space
    /*
    /**
 * Similar to 347. Top K Frequent Elements
 * create a min heap. kick out min item everytime heap hits size k
 * For largest elems, we are using min heap. So that we can kick off min element in O(logk) time
 */
public class KthLargestElementinArray {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    //Approach:2: Quick Select; Order Statistics; (N-K) smallest element in the array
    //O(n^2) time, @(n) time, O(1) space
    //TODO: Implement
}
