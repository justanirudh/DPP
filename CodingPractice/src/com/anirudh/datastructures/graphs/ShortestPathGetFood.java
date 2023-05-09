package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 9/4/21.
 */
/*
1730. Shortest Path to Get Food
Medium

158

7

Add to List

Share
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your List<Integer>. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current List<Integer> if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.


Example 1:


Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 dist to reach the food.
Example 2:


Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.
Example 3:


Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 dist to reach the bottom food.
Example 4:

Input: grid = [["O","*"],["#","O"]]
Output: 2
Example 5:

Input: grid = [["X","*"],["#","X"]]
Output: -1
 */

//BFS in a matrix
public class ShortestPathGetFood {

    char[][] grid;
    boolean[][] visited;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;

    }

    int doBFS(int[] point) {
        visited[point[0]][point[1]] = true;
        int dist = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(point);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int j = 0; j < len; ++j) { //using similar to level order traversal
                int[] curr = queue.poll();
                for (int i = 0; i < 4; ++i) {
                    assert curr != null;
                    int x = curr[0] + dx[i];
                    int y = curr[1] + dy[i];
                    int[] p = {x, y};
                    if (isValid(x, y) && grid[x][y] != 'X' && !visited[x][y]) {
                        if (grid[x][y] == '#')
                            return dist + 1;
                        else { // is unvisited '0'
                            visited[x][y] = true;
                            queue.offer(p);
                        }
                    }
                }
            }
            dist++; //increment distance after each "LEVEL"; dist of all nodes in the q right now
        }
        return -1;
    }

    public int getFood(char[][] grid) {

        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];
        int[] user = new int[2];

        //find person
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '*') {
                    user[0] = i;
                    user[1] = j;
                    break;
                }
            }
        }
        return doBFS(user);
    }
}
