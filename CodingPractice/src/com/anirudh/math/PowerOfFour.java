package com.anirudh.math;

/**
 * Created by paanir on 10/22/17.
 */
/*
342. Power of Four
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

 */
public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if ((num & (num - 1)) != 0)
            return false;
        else {
            //number of zeroes on the right side of the only 1 should be even
            int numZeroes = 0;
            while (num != 1) {
                num = num >> 1;
                numZeroes++;
            }
            return numZeroes % 2 == 0;
        }
    }
}
