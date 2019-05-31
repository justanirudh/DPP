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
        //0x55555555 = 0101 0101 0101 0101 0101 0101 0101 0101 (32 bit)

        //explanation: if power of four, 1 is in an odd position in the binary rep
        //so we and 'num' with a number that 1 in all odd positions
        // the resulting number should be the num itself

        //eight 5's in the hexadecimal
        return num > 0 && (num & (num - 1)) == 0 && ((num & 0x55555555) == num);
    }

    //TLE
    //O(log K)
    public boolean isPowerOfFourSlow(int num) {
        if ((num & (num - 1)) != 0) //is power of two
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

    public static void main(String[] args) {
        System.out.println(0x55555555);
    }
}
