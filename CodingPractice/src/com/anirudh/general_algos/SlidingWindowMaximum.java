package com.anirudh.general_algos;

/**
 * Created by paanir on 2/5/17.
 */
/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 */
//better algo: http://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
    //USe AVL/Red-black tree: O((n-k)logk)
public class SlidingWindowMaximum {

    //O((n-k) * k )
    public static int[] maxSlidingWindowBad(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];
        int[] maxes = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; ++i) {
            System.out.println("here");
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; ++j) {
                if (nums[j] > max)
                    max = nums[j];
            }
            maxes[i] = max;
        }
        return maxes;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];

        int[] maxes = new int[nums.length - k + 1];

        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int j = 0; j < k; ++j) {
            if (nums[j] > maxValue) {
                maxValue = nums[j];
                maxIndex = j;
            }
        }
        maxes[0] = maxValue;

        for (int i = 1; i <= nums.length - k; ++i) {
            int newIndex = i + k - 1;
            int newNum = nums[newIndex];
            if (maxIndex >= i && maxIndex < newIndex) {
                if (newNum > maxValue) {
                    maxValue = newNum;
                    maxIndex = newIndex;
                }
                maxes[i] = maxValue;
            } else {
                int max = Integer.MIN_VALUE;
                for (int j = i; j < i + k; ++j) {
                    if (nums[j] > max) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }
                maxValue = max;
                maxes[i] = maxValue;
            }
        }
        return maxes;
    }

    public static void main(String[] args) {
        int[] arr = maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
