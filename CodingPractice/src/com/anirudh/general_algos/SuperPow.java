package com.anirudh.general_algos;

import java.util.Arrays;

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
//Still wrong
public class SuperPow {
    public int superPowNaive(int a, int[] b) {
        int len = b.length;
        double prod = 1;
        for(int i = 0; i < len; ++i){
            prod = prod * Math.pow(a, b[i] * Math.pow(10, len - i - 1));
        }
        return (int)prod % 1337;
    }

//-----------------------------------------------------

    static int powerAndMod(int a, int b){
        return (int)Math.pow(a, b) % base;
    }

    //ab % k = (a%k)(b%k)%k
    //Also, c^(m+n) = c^m * c^n
    static int base = 1337;
    public static int superPow(int a, int[] b) {
        int len = b.length;
        int last = b[len - 1];
        b = Arrays.copyOf(b, len-1);
        return powerAndMod( superPow(a, b), 10) * (powerAndMod(a, last)) % base;
    }
}
