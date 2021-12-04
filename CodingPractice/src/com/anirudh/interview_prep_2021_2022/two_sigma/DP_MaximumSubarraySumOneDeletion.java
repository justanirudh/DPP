package com.anirudh.interview_prep_2021_2022.two_sigma;

/*
1186. Maximum Subarray Sum with One Deletion
Medium

1005

35

Add to List

Share
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one
element deletion. In other words, you want to choose a subarray and optionally delete one element from it so
that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.


Example 1:

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
Example 2:

Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.
Example 3:

Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it,
then get an empty subarray to make the sum equals to 0.
 */

/*
Its like Kadane + Array Product without Self
1. Create 2 arrays: maxEndingHere (Kadane) and maxStartingHere(reverse Kadane)
2. Go from front to back and fill Kadane ARRAYs. Also get max NUMBER
3. Max is required in case we dont want to delete anything, as problem says we can delete ATMOST 1 number
4. Go from back to front and calculate maxStartingHere array
5. Now iterate through array [1 to len-2] and find Max(max, maxStartingHere[i-1] + maxEndingHere[i+1])
 */
public class DP_MaximumSubarraySumOneDeletion {
    public int maximumSum(int[] arr) {

        int max = arr[0];
        int[] maxEndingHere = new int[arr.length];
        maxEndingHere[0] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            maxEndingHere[i] = Math.max(maxEndingHere[i - 1] + arr[i], arr[i]);
            max = Math.max(max, maxEndingHere[i]); //option of no deletion
        }

        int[] maxStartingHere = new int[arr.length];
        maxStartingHere[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; --i) {
            maxStartingHere[i] = Math.max(maxStartingHere[i + 1] + arr[i], arr[i]);
        }

        for (int i = 1; i < arr.length - 1; ++i) { //i is the number tht will be deleted
            int maxSumdeleteI = maxEndingHere[i - 1] + maxStartingHere[i + 1];
            max = Math.max(max, maxSumdeleteI);
        }
        return max;
    }
}
