package com.anirudh.interview_prep_2021_2022.google.lc_last_6m;

import java.util.Arrays;

/**
 * Created by paanir on 9/2/21.
 */
/*
1509. Minimum Difference Between Largest and Smallest Value in Three Moves
Medium

649

85

Add to List

Share
Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.

Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.



Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
The difference between the maximum and minimum is 1-0 = 1.
Example 3:

Input: nums = [6,6,0,1,1,4,6]
Output: 2
Example 4:

Input: nums = [1,5,6,14,15]
Output: 1
 */
/*
First sort the array
We have to minimize difference between largest and smallest. So by intuition we can do any of these in 3 moves:
1. Decrease the largest 3 elements [to the 4th largest value]
2. Decrease largest 2 elems (to 3rd largest value) and increase smallest 1 (to 2nd smallest value)
3. Decrease largest elem (to 2nd largest) and increase smallest 2 elems (to 3rd smallest)
4. Increase smallest 3 (to 4th smallest value)
Then find Min[1,2,3,4]
 */
public class MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves {

    public int minDifference(int[] nums) {
        //for <= 3 elems, convert all to 0
        //for 4 elems, convert 3 of the numbers equal to the 4th elem
        if(nums.length <= 4 )
            return 0;

        Arrays.sort(nums);
        int res1 = nums[nums.length - 4] - nums[0];
        int res2 = nums[nums.length - 3] - nums[1];
        int res3 = nums[nums.length - 2] - nums[2];
        int res4 = nums[nums.length - 1] - nums[3];

        return Math.min(Math.min(res1, res2), Math.min(res3, res4));


    }
}
