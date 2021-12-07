package com.anirudh.companies_21_22.two_sigma;

import java.util.HashMap;
import java.util.Map;

/*
70. Climbing Stairs
Easy

8186

240

Add to List

Share
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */
/*
Use DP with memoization
climb(n) = number of ways to reach n
climb(n) = climb(n-1) + climb(n-2)
 */
public class DP_ClimbingStairs {
    Map<Integer, Integer> memo = new HashMap<>(); //k -> number of ways to reach k

    int climb(int k) {
        if (memo.containsKey(k))
            return memo.get(k);
        int numWays = climb(k - 1) + climb(k - 2);
        memo.put(k, numWays);
        return numWays;
    }

    public int climbStairs(int n) {
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 2);
        return climb(n);
    }
}
