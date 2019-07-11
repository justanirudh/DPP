package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 7/3/17.
 */
/*
55. Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 */
public class JumpGame {

    //-----------------------74/75 TLE---------------------------
    //Dynamic programming
    boolean[] canJumpArr;
    int[] nums;

    public boolean canJumpAux(int index) {
        if (index == 0)
            return true;
        for (int i = 0; i <= index - 1; ++i) {
            if (canJumpArr[i] && index - i <= nums[i]) {
                canJumpArr[index] = true;
                return true;
            }
        }
        return false;
    }

    public boolean canJumpSlow(int[] nums) {
        this.nums = nums;
        if (nums == null || nums.length == 0 || nums.length == 1)
            return true;
        canJumpArr = new boolean[nums.length];
        canJumpArr[0] = true;
        for (int i = 0; i <= nums.length - 2; ++i) {
            //can jump till the previous index and then diff b/w indices <= value at i
            if (canJumpAux(i) && (nums.length - 1) - i <= nums[i])
                return true;
        }
        return false;
    }
    //-------------------------------------------------------------

    //smarter approach
    public boolean canJump(int[] A) {
        //max is the maximum index we can jump to so far
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            //At any point, check if the index is greater than maximum jump to an index we can take
            //if i > maximum we can reach till here, this means we cannot go any farther
            if (i > max) {
                return false;
            }

            max = Math.max(i + A[i], max);
        }
        return true;
    }

}
