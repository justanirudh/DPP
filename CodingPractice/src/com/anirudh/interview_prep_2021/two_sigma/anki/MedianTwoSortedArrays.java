package com.anirudh.interview_prep_2021.two_sigma.anki;

/*
4. Median of Two Sorted Arrays
Hard

12488

1695

Add to List

Share
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
Example 3:

Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000
Example 4:

Input: nums1 = [], nums2 = [1]
Output: 1.00000
Example 5:

Input: nums1 = [2], nums2 = []
Output: 2.00000
 */

/*
We need to find a partition of arrays X and Y
2 conditions should hold true:
partitionX = elements in a partition in X
partitionY = elements in a partition in Y

1. partitionX + partitionY = (lenX + lenY + 1)/2
2. max(LeftX) <= min(RightY) && max(LeftY) <= min(RightX)

We do binary search in the smaller of the 2 arrays to give time_cx as O(log(min(lenX, lenY)))
 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        return 0;
    }
}
