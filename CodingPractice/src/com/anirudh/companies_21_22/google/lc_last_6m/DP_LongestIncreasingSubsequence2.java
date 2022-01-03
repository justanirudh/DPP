package com.anirudh.companies_21_22.google.lc_last_6m;

/*
300. Longest Increasing Subsequence
Medium

10017

203

Add to List

Share
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */

/*
It appears the best way to build an increasing subsequence is: for each element num,
if num is greater than the largest element in our subsequence, then add it to the subsequence.
Otherwise, perform a linear scan through the subsequence starting from the smallest element and replace the
first element that is greater than or equal to num with num. This opens the door for elements that are greater
than num but less than the element replaced to be included in the sequence.

One thing to add: this algorithm does not always generate a valid subsequence of the input, but the length of
the subsequence will always equal the length of the longest increasing subsequence. For example, with the
input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5], which isn't a subsequence, but the length is still correct.
 The length remains correct because the length only changes when a new element is larger than any element in the subsequence.
 In that case, the element is appended to the subsequence instead of replacing an existing element.

 Tx: O (nlogn)

 */
import java.util.ArrayList;
import java.util.List;

public class DP_LongestIncreasingSubsequence2 {

    int binarySearch(List<Integer> list, int num) {
        int l = 0;
        int r = list.size() - 1;
        while(l < r) {
            int mid = l + (r-l)/2;
            if(list.get(mid) == num)
                return mid;
            else if (list.get(mid) > num) {
                r = mid; //answer can be mid, so keep it in boundary
            }
            else
                l = mid + 1;
        }
        return r; // or l
    }

    public int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] > sub.get(sub.size() - 1)) { //num bigger than biggest in sub
                sub.add(nums[i]);
            }
            else { //replace smallest elem in sub greater than num
                int idx = binarySearch(sub, nums[i]);
                sub.set(idx, nums[i]);
            }
        }
        return sub.size();
    }
}
