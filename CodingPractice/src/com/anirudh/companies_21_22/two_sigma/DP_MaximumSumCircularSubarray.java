package com.anirudh.companies_21_22.two_sigma;

/**
 * Created by paanir on 9/5/21.
 */

/*
918. Maximum Sum Circular Subarray
Medium

1945

82

Add to List

Share
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.



Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: nums = [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: nums = [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: nums = [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 */

/*
1. If the max subarray is from somewhere in the middle: just simple kadane's algo
2. Other option is max subarray is from some part in the start and some part in the end
2.1. For this case, we can find min subarray in the middle.
2.2 Then totalSum - min subarray sum = MAX subarray sum!
3. We can find max of 1 or 2
4. If all nums are -ve, (4.1) min = totalSum && (4.2)max = min_element_of_array (also -ve)
4.1 means totalSum-min = 0; but problem says atleast 1 elem
4.2 Hence, if max is -ve, return max
 */
public class DP_MaximumSumCircularSubarray {

    public int maxSubarraySumCircular(int[] nums) {

        //MAX kadane and MIN kadane
        int currMax = nums[0];
        int max = nums[0];
        int currMin = nums[0];
        int min = nums[0];
        int sum = nums[0];
        for(int i = 1; i < nums.length; ++i){
            currMax = Math.max(currMax + nums[i], nums[i]); //either take curr num in subarray, or throw away the subaaray until now
            max = Math.max(currMax, max);
            currMin = Math.min(currMin + nums[i], nums[i]); //either take curr num in subarray, or throw away the subaaray until now
            min = Math.min(currMin, min);
            sum += nums[i];
        }

        return max < 0 ? max : Math.max(max, sum - min); // 4. above
    }
}
