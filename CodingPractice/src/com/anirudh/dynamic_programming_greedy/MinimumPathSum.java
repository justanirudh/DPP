package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 5/10/17.
 */
/*
64. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum
of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int rl = grid.length;
        int cl = grid[0].length;
        int[][] dists = new int[rl][cl]; //same dimensions as grid
        dists[0][0] = grid[0][0];
        for (int i = 1; i < cl; ++i) //populating 0th row
            dists[0][i] = dists[0][i - 1] + grid[0][i];
        for (int i = 1; i < rl; ++i)//populating 0th col
            dists[i][0] = dists[i - 1][0] + grid[i][0];
        for (int r = 1; r < rl; ++r) {
            for (int c = 1; c < cl; ++c) { //populating rest of matrix
                dists[r][c] = Math.min(dists[r - 1][c], dists[r][c - 1]) + grid[r][c];
            }
        }
        return dists[rl - 1][cl - 1];
    }
}
