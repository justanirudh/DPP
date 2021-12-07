package com.anirudh.companies_21_22.spotify;

/*
509. Fibonacci Number
Easy

1947

238

Add to List

Share
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).



Example 1:

Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 */

import java.util.HashMap;
import java.util.Map;

/*
Use recursion + memoization
 */
public class FibonacciNumber {

    Map<Integer, Integer> memo = new HashMap<>();

    public int fibAux(int n) {
        if (memo.containsKey(n))
            return memo.get(n);
        int fib = fibAux(n - 1) + fibAux(n - 2);
        memo.put(n, fib);
        return fib;
    }

    public int fib(int n) {
        memo.put(0, 0);
        memo.put(1, 1);
        return fibAux(n);
    }
}
