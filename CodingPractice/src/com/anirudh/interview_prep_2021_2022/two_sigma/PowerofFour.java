package com.anirudh.interview_prep_2021_2022.two_sigma;

/*
342. Power of Four
Easy

1061

264

Add to List

Share
Given an integer n, return true if it is a power of four. Otherwise, return false.

An integer n is a power of four, if there exists an integer x such that n == 4x.



Example 1:

Input: n = 16
Output: true
Example 2:

Input: n = 5
Output: false
Example 3:

Input: n = 1
Output: true


Constraints:

-231 <= n <= 231 - 1


Follow up: Could you solve it without loops/recursion?
 */

/*
a = 4^x
log2 a = xlog2 4
log2 a = 2x
x = (log2 a) / 2
hence log2 a should be divisible by 2
 */
public class PowerofFour {

    public boolean isPowerOfFourLoop(int n) {
        if (n == 1)
            return true;
        while (n % 4 == 0) {
            n = n / 4;
        }
        return n == 1;
    }

    public boolean isPowerOfFour(int n) {
        return n != 0 && ((Math.log(n)/Math.log(2)) % 2 == 0);
    }

}
