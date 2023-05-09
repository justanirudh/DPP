package com.anirudh.a_prep_2023.bloomberg;

import java.util.Arrays;

/*
611. Valid Triangle Number
Medium
3.4K
185
company
Bloomberg
company
LinkedIn
company
Microsoft
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.



Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Example 2:

Input: nums = [4,2,3,4]
Output: 4
 */

/*
1. sort the array
2. iterate from last to first
. . . . .. .. . . . .
  a         b    c
  for triangle cond:
  c+b > a: already true bcoz sorted, c>a so c+ any num > a
  c+a > b: same as above
  a+b > c: This needs to be verified
  if a + b <= c, increment start to try with a bigger num
  if a+b > c, found a solution + all indices between a and b will satisfy,
  if a+ b > c then
  (a +a1) + b > c
  (a + a2) + b > c
  hence all pairs, keeping b and c as constant and incrementing a, will suffice, hence (end - start)

  Tx: O(n^2)
  Sx: O(1)
 */
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; --i) {
            int c = nums[i];
            int start = 0;
            int end = i - 1;
            while (start < end) {
                if (nums[start] + nums[end] <= c) {
                    start++;
                } else { // s + e > c
                    res += end - start;
                    end--;
                }
            }
        }
        return res;
    }
}
