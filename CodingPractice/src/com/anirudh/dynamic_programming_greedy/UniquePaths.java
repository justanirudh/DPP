package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 5/11/17.
 */
/*
62. Unique Paths
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner
of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        for (int i = 0; i < n; ++i) //entire first row
            ways[0][i] = 1;
        for (int i = 1; i < m; ++i) //entire first column
            ways[i][0] = 1;
        for (int i = 1; i < m; ++i) { //rest of
            for (int j = 1; j < n; ++j) {// the grid
                ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
            }
        }
        return ways[m - 1][n - 1];
    }

    //O(m + n) but might lead to integer overflow
    public double fact(double n,double res){
        if(n == 0)
            return res;
        else
            return fact(n-1, n * res);
    }
    public int uniquePaths2(int m, int n) {
        //(m-1+n-1)C(n-1) = (m+n-2)C(n-1) = (m+n-2)!/(m-1)!(n-1)!
        return (int)(fact(m+n-2, 1)/(fact(m-1, 1) * fact(n-1, 1)));
    }
}
