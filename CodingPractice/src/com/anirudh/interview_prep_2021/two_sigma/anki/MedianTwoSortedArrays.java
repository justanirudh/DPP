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

leftPartitionX = num_elements in a left partition of X
leftPartitionY = num_elements in a left partition in Y

2 conditions should hold true:
1. LeftPartitionX + LeftPartitionY = (lenX + lenY + 1)/2; Using +1 since it plays well with odd and even nums
2. For the correct partition, maxLeftX <= minRightY && maxLeftY <= minRightX

We always make sure LeftPartitionX >= LeftPartitionY, as a rule
We do binary search in the smaller of the 2 arrays to give time_cx as O(log(min(lenX, lenY)))

Algo:
    LeftPartitionX + LeftPartitionY = (lenX + lenY + 1)/2

    Found: maxLeftX <= minRightY && maxLeftY <= minRightX
        If total num_elems is even, median = (max(maxLeftX, maxLeftY) + min(minRightX, minRightY))/2
        if total num_elems in odd, median = max(maxLeftX, maxLeftY)
    else if (maxLeftX > minRightY), go towards the left of the x array to find smaller x values
    else go towards the right in x array

 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int lenX = nums1.length;
        int lenY = nums2.length;

        int start = 0; //x is guaranteed to be shorter array
        int end = lenX;
        while (start <= end) {
            int leftPartitionX = start + (end - start) / 2;
            int leftPartitionY = (lenX + lenY + 1) / 2 - leftPartitionX; //make sure lenX + lenY + 1, NOT lenX + lenY

            int maxLeftX = leftPartitionX == 0 ? Integer.MIN_VALUE : nums1[leftPartitionX - 1];
            int minRightX = leftPartitionX == lenX ? Integer.MAX_VALUE : nums1[leftPartitionX];

            int maxLeftY = leftPartitionY == 0 ? Integer.MIN_VALUE : nums2[leftPartitionY - 1];
            int minRightY = leftPartitionY == lenY ? Integer.MAX_VALUE : nums2[leftPartitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //Found
                if ((lenX + lenY) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) //move towards smaller values in x, i.e. left
                end = leftPartitionX - 1;
            else
                start = leftPartitionX + 1;
        }
        return 0;
    }
}
