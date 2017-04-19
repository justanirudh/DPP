package com.anirudh.general_algos;

/**
 * Created by paanir on 4/18/17.
 */
/*
372. Super Pow
Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer
given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
 */
//Under-construction
public class SuperPow {
    public int superPowNaive(int a, int[] b) {
        int len = b.length;
        double prod = 1;
        for(int i = 0; i < len; ++i){
            prod = prod * Math.pow(a, b[i] * Math.pow(10, len - i - 1));
        }
        return (int)prod % 1337;
    }
}
