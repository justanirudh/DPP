package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 8/28/17.
 */
/*
45. Jump Game II
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1)
            return 0;
        int jumps = 0;
        int jumpFurthest = 0;
        int jumpEnd = 0;
        for (int i = 0; i < nums.length - 1; ++i) { //till -1 because only need to reach last index
            jumpFurthest = Math.max(i + nums[i], jumpFurthest); //max jump that can be done till now
            if (i == jumpEnd) { //if reach last max jump
                jumps++; //increment jump
                jumpEnd = jumpFurthest; //set new limit of max jump length
            }
        }
        return jumps;
    }
}
