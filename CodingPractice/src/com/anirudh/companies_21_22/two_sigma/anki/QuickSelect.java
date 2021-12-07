package com.anirudh.interview_prep_2021.spotify.anki;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 * Medium
 * <p>
 * 6627
 * <p>
 * 396
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
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
//Approach:2: Quick Select; Order Statistics; (N-K) smallest element in the array
//O(n^2) time, @(n) time, O(1) space
public class QuickSelect {
    int[] nums;
    int k;
    Random rand;

    void swap(int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    int partition(int start, int end) {
        int pivotIdx = start + rand.nextInt(end - start);
        int pivot = nums[pivotIdx];
        swap(start, pivotIdx);
        int left = start + 1;
        for (int right = left; right <= end; ++right) {
            if (nums[right] < pivot) {
                swap(left, right);
                left++;
            }
        }
        left--;
        swap(left, start);
        return left;
    }

    int quickSelect(int start, int end) {
        if (start < end) {
            int partIndex = partition(start, end);
            if (partIndex == k)
                return nums[partIndex];
            else if (partIndex < k)
                return quickSelect(partIndex + 1, end);
            else
                return quickSelect(start, partIndex - 1);
        }
        return nums[start];
    }

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k; //kth largest is N-k + 1 smallest, meaning at index N-k
        rand = new Random();
        return quickSelect(0, nums.length - 1);
    }

}
