package com.anirudh.companies_21_22.google.lc_last_6m;

/*
407. Trapping Rain Water II
Hard

2397

54

Add to List

Share
Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the
volume of water it can trap after raining.



Example 1:


Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
Example 2:


Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Start with the boundary elems, among them start with one with smallest height
Do BFS
For each cell, get its 4 neighbours
the neighbours will store water if possible w.r.t current cell's height
the neighbour's height will also be updated as now it has brick + water to create a new wall
 */
public class TrappingRainwaterII {

    class Cell {
        int x;
        int y;
        int height;

        Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    class CompareCells implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            return a.height - b.height;
        }
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    boolean[][] visited; //cells in visited will NOT be storing any more water
    int numRows;
    int numCols;

    boolean isValid(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    public int trapRainWater(int[][] heightMap) {
        numRows = heightMap.length;
        numCols = heightMap[0].length;

        visited = new boolean[numRows][numCols];
        int res = 0;

        Queue<Cell> queue = new PriorityQueue<>(new CompareCells()); //will contain all VISITED elems

        //create boundary
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (i == 0 || j == 0 || i == numRows - 1 || j == numCols - 1) { //if boundary
                    queue.offer(new Cell(i, j, heightMap[i][j]));
                    res += 0; //add 0 water
                    visited[i][j] = true; //visited has all cells in which NO MORE water can be added
                }
            }
        }

        while (!queue.isEmpty()) { //Do BFS to get water trapped in each neighbour
            Cell curr = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int x = curr.x + dx[i];
                int y = curr.y + dy[i];
                if (isValid(x, y) && !visited[x][y]) {
                    res += Math.max(0, curr.height - heightMap[x][y]); //add water to neighbour
                    visited[x][y] = true;
                    queue.offer(new Cell(x, y, Math.max(curr.height, heightMap[x][y])));
                }
            }
        }

        return res;
    }
}
