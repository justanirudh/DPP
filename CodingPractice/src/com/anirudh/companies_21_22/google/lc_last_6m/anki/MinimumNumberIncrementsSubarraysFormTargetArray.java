package com.anirudh.companies_21_22.google.lc_last_6m.anki;

/*
1526. Minimum Number of Increments on Subarrays to Form a Target Array
Hard

759

48

Add to List

Share
You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.

In one operation you can choose any subarray from initial and increment each value by one.

Return the minimum number of operations to form a target array from initial.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: target = [1,2,3,2,1]
Output: 3
Explanation: We need at least 3 operations to form the target array from the initial array.
[0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
[1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
[1,2,2,2,1] increment 1 at index 2.
[1,2,3,2,1] target array is formed.
Example 2:

Input: target = [3,1,1,2]
Output: 4
Explanation: [0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
Example 3:

Input: target = [3,1,5,4,2]
Output: 7
Explanation: [0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2].


Constraints:

1 <= target.length <= 105
1 <= target[i] <= 105
Accepted
22,442
Submissions

 */

/*
Each number is a vertical wall of bricks
total number of LEFT edges of these bricks is the number of subarray-increment operations
https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/discuss/754682/Wall-of-bricks

start with res = arr[0]
for every pair elem a[i], a[i + 1]
res += math.max(a[i+1] - a[i], 0)

 */
public class MinimumNumberIncrementsSubarraysFormTargetArray {
    public int minNumberOperations(int[] target) {
        int res = target[0];
        for(int i = 0; i < target.length - 1; ++i) {
            res += Math.max(target[i + 1] - target[i], 0);
        }
        return res;
    }
}
