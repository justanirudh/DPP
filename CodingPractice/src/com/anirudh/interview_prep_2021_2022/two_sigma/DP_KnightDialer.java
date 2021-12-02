package com.anirudh.interview_prep_2021_2022.two_sigma;

/*
935. Knight Dialer
Medium

1084

318

Add to List

Share
The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:


We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.



Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3
Output: 46
Example 4:

Input: n = 4
Output: 104
Example 5:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Similar to Climbing Stairs
From a position, knight can go to 8 different pos; So, from 8 diff position, curr position can be reached
State(i,j,k) -> number of ways to reach (i,j) in k DIGITS
Map {(i,j,k) -> numWays}
base case: {i,j,1} = 1; as if we only need 1 number, just place it on any square, so only 1 way
Use LONG everywhere
 */
public class DP_KnightDialer {

    Map<List<Integer>, Long> memo = new HashMap<>();
    char[][] numpad = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'*', '0', '#'}};
    int max = (int)Math.pow(10, 9) + 7;

    boolean isNotValid(int i, int j) {
        return i < 0 || i >= 4 || j < 0 || j >= 3 || numpad[i][j] == '*' || numpad[i][j] == '#';
    }

    long numWays(int i, int j, int k) {
        if (isNotValid(i, j))
            return 0;
        if (k == 1)
            return 1;
        List<Integer> state = Arrays.asList(i, j, k);
        if (memo.containsKey(state))
            return memo.get(state);
        long numSteps =
                numWays(i - 2, j + 1, k - 1) +
                        numWays(i - 1, j + 2, k - 1) % max +
                        numWays(i + 1, j + 2, k - 1) % max +
                        numWays(i + 2, j + 1, k - 1) % max +
                        numWays(i + 2, j - 1, k - 1) % max +
                        numWays(i + 1, j - 2, k - 1) % max +
                        numWays(i - 1, j - 2, k - 1) % max +
                        numWays(i - 2, j - 1, k - 1) % max;
        memo.put(state, numSteps);
        return numSteps;
    }

    public int knightDialer(int n) {
        long res = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                res = (res + numWays(i, j, n)) % max;
            }
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(new DP_KnightDialer().knightDialer(10));
    }
}

