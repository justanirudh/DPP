package com.anirudh.general_algos;

/**
 * Created by paanir on 5/28/17.
 */
/*
326. Power of Three
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
 */
public class powerOfThree {

    public boolean isPowerOfThree(int n) {
        int num = n;
        if (num < 1)
            return false;
        while (num != 1) {
            if (num % 3 != 0) {
                return false;
            }
            num /= 3;
        }
        return true;
    }

    //without loop/recursion
    public boolean isPowerOfThreeHacky(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }
}
