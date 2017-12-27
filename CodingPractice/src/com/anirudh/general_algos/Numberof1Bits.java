package com.anirudh.general_algos;

/**
 * Created by paanir on 12/23/17.
 */
/*
191. Number of 1 Bits
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
 */
public class Numberof1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res += n & 1;
            n = n >> 1;
        }
        return res;
    }
}
