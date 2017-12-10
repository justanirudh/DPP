package com.anirudh.general_algos;

import java.util.Deque;
import java.util.LinkedList;

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
// Can also use AVL/Red-black tree: O((n-k)logk)
public class SlidingWindowMaximum {

    //O(k) space and O(n) (amortized) time
    static public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
            return new int[0];
        int len = nums.length;
        int[] res = new int[len - k + 1]; //result
        Deque<Integer> deq = new LinkedList<>(); //indices in inc order + only those necessary
        for (int i = 0; i < len; ++i) {
            //remove all not in window from head (older elems)
            while (!deq.isEmpty() && deq.peek() < i - k + 1)
                deq.poll();
            //remove all who are less than current. remove from tail (newer elems)
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i])
                deq.pollLast();
            //add new index to queue
            deq.offer(i);
            //add to res after i >=k - 1 (for first k-1 elements, no addition in res )
            if (i >= k - 1)
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
