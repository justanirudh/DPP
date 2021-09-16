package com.anirudh.interview_prep_2021.spotify.anki;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
994. Rotting Oranges
Medium

4325

231

Add to List

Share
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {
    int[][] grid;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        int maxTime = Integer.MIN_VALUE;
        Deque<List<Integer>> queue = new ArrayDeque<>();
        boolean allRotten = true;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 2) {
                    List<Integer> point = new ArrayList<>();
                    point.add(i); //x
                    point.add(j); //y
                    point.add(0); //distance
                    queue.offer(point);
                }
                if(grid[i][j] == 1)
                    allRotten = false;
            }
        }
        if(allRotten)
            return 0;
        //queue has all rotten oranges
        while (!queue.isEmpty()) {
            List<Integer> curr = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int x = curr.get(0) + dx[i];
                int y = curr.get(1) + dy[i];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                    grid[x][y] = 2; //change to rotten
                    List<Integer> next = new ArrayList<>();
                    next.add(x);
                    next.add(y);
                    int time = curr.get(2) + 1;
                    next.add(time);
                    queue.offer(next); //add to queue
                    maxTime = Math.max(maxTime, time);
                }
            }
        }

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }
        return maxTime;
    }
}
