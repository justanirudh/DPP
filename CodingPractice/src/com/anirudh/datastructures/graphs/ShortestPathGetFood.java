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
    Set<List<Integer>> visited = new HashSet<>(); //DONT use set of arrays. hashcode of each aray is diff even is elems are same
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;

    }

    int doBFS(List<Integer> point) {
        visited.add(point);
        int dist = 0;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(point);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int j = 0; j < len; ++j) { //using similar to level order traversal
                List<Integer> curr = queue.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = curr.get(0) + dx[i];
                    int y = curr.get(1) + dy[i];
                    List<Integer> p = new ArrayList<>();
                    p.add(x);
                    p.add(y);
                    if (isValid(x, y) && grid[x][y] != 'X' && !visited.contains(p)) {
                        if (grid[x][y] == '#')
                            return dist + 1;
                        else { // is unvisited '0'
                            visited.add(p);
                            queue.offer(p);

                        }
                    }
                }
            }
            dist++; //increment distance after each "LEVEL"
        }
        return -1;
    }

    public int getFood(char[][] grid) {

        this.grid = grid;
        List<Integer> user = new ArrayList<>();

        //find person
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '*') {
                    user.add(i);
                    user.add(j);
                    break;
                }
            }
        }
        return doBFS(user);
    }
}
