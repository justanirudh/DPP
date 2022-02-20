package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
162. Find Peak Element
Medium

5299

3493

Add to List

Share
A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 */

/*
Approach 1: binary search
if elem[mid] < elem[mid + 1]
    l = mid + 1;
else if elem[mid] > elem[mid + 1]
    r = mid //as mid can also be peak

Approach 2: linear scan
only check with next elem
if(elem[i] > elem[i+1]), i is the peak elem
dont need to compare i-1 and i because if i-1 > i, then i-1 would have been peak (1st elem is peak if it is greater than 2nd elem)
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int mid = s + (e-s)/2;
            if(nums[mid] < nums[mid + 1])
                s = mid + 1; //as mid cannot be peak
            else
                e = mid;
        }
        return s; //or e
    }

}
