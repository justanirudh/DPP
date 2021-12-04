package com.anirudh.subarray_substring;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by paanir on 2/5/17.
 */
/*
239. Sliding Window Maximum
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

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
/*
first element of the deque should always be the index of the max element of the current window
loop1: for a new element, before inserting it, remove all indices that are out of the window
loop2: for a new element, before inserting, we remove all elements in the deque smaller than the current element
before inserting the new element (2nd while loop)
loop1 should be run before loop2 so that we remove invalid elements first
 */
public class SlidingWindowMaximum {

    //O(k) space and O(n) (amortized) time
    static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
            return new int[0];
        int len = nums.length;
        int[] res = new int[len - k + 1]; //result

        Deque<Integer> deq = new ArrayDeque<>(); //indices in inc order && only those necessary
        for (int i = 0; i < len; ++i) {
            while (!deq.isEmpty() && deq.peek() <= i - k ) //remove all not in window from head (older elems)
                deq.poll();
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i]) //remove all who are less than current. remove from tail (newer elems)
                deq.pollLast();
            deq.offer(i); //add new index to queue
            if (i < k - 1) //add to res after i >=k - 1 (for first k-1 elements, no addition in res )
                continue;
            res[i - k + 1] = nums[deq.peek()];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
