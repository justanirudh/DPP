package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;
/*
1091. Shortest Path in Binary Matrix
Medium

2014

109

Add to List

Share
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */

import java.util.ArrayDeque;
import java.util.Queue;

/*
Do BFS in a matrix in 8-direction way
 */
public class ShortestPathinBinaryMatrix {
    int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
    int[][] grid;

    boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;
        if (grid == null || grid.length == 0 || grid[0][0] != 0)
            return -1;
        if(grid.length == 1 && grid[0].length == 1) //[[0]]
            return 1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];//false to start with
        Queue<int[]> q = new ArrayDeque<>();
        int[] origin = {0, 0};
        q.offer(origin);
        visited[0][0] = true;
        int length = 1;
        while (!q.isEmpty()) {
            int qLen = q.size();
            for (int j = 0; j < qLen; ++j) {
                int[] node = q.poll();
                for (int i = 0; i < 8; ++i) {
                    int x = dx[i] + node[0];
                    int y = dy[i] + node[1];
                    if (isValid(x, y) && grid[x][y] == 0 && !visited[x][y]) {
                        if (x == grid.length - 1 && y == grid[0].length - 1)
                            return length + 1;
                        visited[x][y] = true;
                        int[] next = {x, y};
                        q.offer(next);
                    }
                }
            }
            length++;
        }
        return -1;
    }
}
