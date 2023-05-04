package com.anirudh.a_prep_2023.bloomberg;

import com.anirudh.datastructures.graphs.Coord;
import com.anirudh.datastructures.graphs.GraphNode;

import java.util.HashMap;

/**
 * Created by paanir on 3/25/17.
 */
//DFS
/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */
public class NumberOfIslands {

    char[][] grid;
    int[] di = {0, 0, 1, -1};
    int[] dj = {1, -1, 0, 0};
    boolean[][] visited;
    int nr;
    int nc;

    private boolean isValid(int x, int y) {
        return x >= 0 && x < nr && y >= 0 && y < nc;
    }

    private void doDFS(int x, int y) {
        visited[x][y] = true;
        for (int k = 0; k < 4; ++k) {
            int xk = x + di[k];
            int yk = y + dj[k];
            if (isValid(xk, yk) && grid[xk][yk] == '1' && !visited[xk][yk]) {
                doDFS(xk, yk);
            }
        }
    }

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.nr = grid.length;
        this.nc = grid[0].length;
        visited = new boolean[nr][nc];
        int res = 0;
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    doDFS(i, j);
                    res++;
                }
            }
        }
        return res;
    }
}
