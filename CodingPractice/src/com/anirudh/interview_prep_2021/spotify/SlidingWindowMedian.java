package com.anirudh.interview_prep_2021.spotify;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by paanir on 9/7/21.
 */
/*
480. Sliding Window Median
Hard

1701

110

Add to List

Share
The median is the middle value in an ordered integer list. If the size of the list is even,
there is no middle value. So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the
very left of the array to the very right. You can only see the k numbers in the window.
Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.



Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 */

/*
IMPORTANT
For Median, always remember the min-heap and max-heap solution
Here use 2 TREESETs of INDICES as they provide these things:
1. Sorted or reverse sorted indices and hence nums
2. removal of a random elem is O(logk) operation. It is O(k) for heap
3. We put index in set as it helps us break ties for equal numbers

lo's size will always be equal or 1 more than hi's size
 */
public class SlidingWindowMedian {

    int[] nums;
    TreeSet<Integer> lo;
    TreeSet<Integer> hi;

    class CompareElems implements Comparator<Integer> {
        public int compare(Integer idx1, Integer idx2) {
            if (nums[idx1] != nums[idx2]) {
                return nums[idx1] - nums[idx2]; //check value then check indices
            } else {
                return idx1 - idx2; //this will help kick out lower index of equal elems first
            }
        }
    }

    private void addAndBalance(int idx) {
        lo.add(idx); //add new elem; then do balancing
        hi.add(lo.pollFirst());
        while (hi.size() > lo.size()) { //this is 'if' in Median of Data Stream
            lo.add(hi.pollFirst());
        }
    }

    double getMedian() {
        return lo.size() == hi.size() ? ((double) nums[lo.first()] + nums[hi.first()]) / 2 : nums[lo.first()]; //use nums[i]
    }


    public double[] medianSlidingWindow(int[] nums, int k) {
        this.nums = nums;
        lo = new TreeSet<>(new CompareElems().reversed()); //treesets to store indices; max set; need to use TreeSet in declaration as we are using TreeSet specific methods
        hi = new TreeSet<>(new CompareElems()); //min set

        for (int i = 0; i < k; ++i) { // //for 1st k elements, balance heap as usual; makes sure lo's size will always be equal or 1 more than hi's size
            addAndBalance(i);
        }

        double[] res = new double[nums.length - k + 1];
        res[0] = getMedian();

        for (int i = k; i < nums.length; ++i) {
            int invalidIdx = i - k; //first remove invalid elem
            if (!lo.remove(invalidIdx)) //log k operation
                hi.remove(invalidIdx);
            addAndBalance(i);
            res[invalidIdx + 1] = getMedian();
        }

        return res;


    }
}
