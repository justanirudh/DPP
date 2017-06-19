package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 6/18/17.
 */
/*
221. Maximal Square
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
 */

// dp(i, j) represents the length of the square
// whose lower-right corner is located at (i, j)
// dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int len = matrix.length;
        int bred = matrix[0].length;
        int[][] dp = new int[len + 1][bred + 1];

        for (int i = 1; i <= len; ++i) {
            for (int j = 1; j <= bred; ++j) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
}
