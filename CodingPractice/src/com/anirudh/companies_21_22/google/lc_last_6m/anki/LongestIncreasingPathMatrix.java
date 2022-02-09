package com.anirudh.companies_21_22.google.lc_last_6m.anki;

/*
329. Longest Increasing Path in a Matrix
Hard

4733

83

Add to List

Share
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
 */

/*
Do DFS in each cell with memoization
Keep a Map {coordinate -> LIS from the coord}
I DONT want to keep visited set as I explore a cell
    because it is a DAG without cycles (as strictly increasing)
    and I WANT overlaps for increasing paths from same cell in different directions
    can overlap which is good

Tx: O(mn): each vertex is only visited once
 */
public class LongestIncreasingPathMatrix {

    int[][] cache; // contains the length of the longest path STARTING from (i,j)
    int[][] matrix;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    boolean isValid(int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    int doDFS(int i, int j) { //gets longest path starting from and including
        if (cache[i][j] != 0)
            return cache[i][j];
        int max = 0; //new cell, dont know length of longest increasing seq from it
        for (int k = 0; k < 4; ++k) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (isValid(x, y) && matrix[x][y] > matrix[i][j]) {
                max = Math.max(max, doDFS(x, y));
            }
        }
        cache[i][j] = max + 1; //+1 is including self elem (i,j)
        return cache[i][j];
    }

    public int longestIncreasingPath(int[][] matrix) {
        cache = new int[matrix.length][matrix[0].length];
        this.matrix = matrix;
        int max = 1;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                max = Math.max(max, doDFS(i, j));
            }
        }
        return max;
    }
}
