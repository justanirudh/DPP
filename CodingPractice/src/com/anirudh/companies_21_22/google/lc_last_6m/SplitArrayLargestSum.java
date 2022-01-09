package com.anirudh.companies_21_22.google.lc_last_6m;

/*
410. Split Array Largest Sum
Hard

3845

120

Add to List

Share
Given an array nums which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.



Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9
Example 3:

Input: nums = [1,4,4], m = 3
Output: 4


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
 */
/*
minimum largest sum = largest element E
maximum largest sum = sum of all elements S
Do binary search between lo = E and hi = S
To start with, ans = S as it is sum of 1 giant subarray
mid = (E+S)/2
make groups with sum <= mid
if num_groups > m => less groups required => each group's sum will increase => lo = mid+1, hi = S
if num_groups < m => more groups required => each group's sum will decrease => lo = E, hi = mid - 1
if num_groups = m => record the value and keep searching for lesser sum => lo = E, hi = mid - 1
Keep doing this until lo > hi
 */
public class SplitArrayLargestSum {
    int[] nums;
    int m;
    int minSum;

    void binarySearch(int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int numSubArrays = 0;
            int currSum = 0;
            for (int n : nums) { //make all the subarrays
                currSum += n;
                if (currSum > mid) { //time to start a new sub_array
                    currSum = n;
                    numSubArrays++;
                }
            }
            numSubArrays++; //to close the last subarray
            if (numSubArrays > m) { //created more than reqd, need to lump more elems together, per split sum will increase, do mid+1
                binarySearch(mid + 1, hi);
            } else { // num_sub_arrays <= m
                minSum = Math.min(minSum, mid); //potential solution
                binarySearch(lo, mid - 1);
            }
        }
    }

    public int splitArray(int[] nums, int m) {
        this.nums = nums;
        this.m = m;

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int n : nums) {
            sum += n;
            if (n > max)
                max = n;
        }

        minSum = sum; //taking whole array-sum as min
        binarySearch(max, sum);

        return minSum;
    }
}
