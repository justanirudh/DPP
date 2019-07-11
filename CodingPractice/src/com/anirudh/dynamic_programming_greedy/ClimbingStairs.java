package com.anirudh.dynamic_programming_greedy;

import java.util.HashMap;

/**
 * Created by paanir on 5/6/17.
 */
/*
70. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
    HashMap<Integer, Integer> hm = new HashMap<>();

    public int climb(int n) {
        if (hm.containsKey(n))
            return hm.get(n);
        else {
            int ways = climbStairs(n);
            hm.put(n, ways);
            return ways;
        }
    }

    public int climbStairs(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else {
            return climb(n - 1) + climb(n - 2);
        }
    }
}
