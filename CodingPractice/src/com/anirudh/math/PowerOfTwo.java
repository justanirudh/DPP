package com.anirudh.math;

/**
 * Created by paanir on 7/21/17.
 */
/*
231. Power of Two
Given an integer, write a function to determine if it is a power of two.
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0)
            return false;
        return (n & (n - 1)) == 0;
    }
}
