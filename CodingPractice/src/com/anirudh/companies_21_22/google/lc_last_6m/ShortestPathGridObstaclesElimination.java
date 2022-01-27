package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;

/**
 * Created by paanir on 9/4/21.
 */
/*
1293. Shortest Path in a Grid with Obstacles Elimination
Hard

1037

18

Add to List

Share
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down,
left, or right from and to an empty cell in one step.

Return the minimum number of dist to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.
 */

/*
Do BFS in matrix
Normally, for BFS in a matrix we use 3 vars: x, y, distance from source (dist)
Here we will use 4 vars: x,y,dist, remaining quota of obstacles (num_obstacles)
first check if quota > manhattan distance. if so, return true
every time we go through an obstacle, decrease it by 1, check if it has been visited, then offer it in queue
EVERYTHING else remains the same

OR create a list of x,y,quota as state key and separate map of state to dist

For State's equals and hashcode, use x,y,num_obstacles (NOT distance)
 */
class ShortestPathGridObstaclesElimination {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int numRows;
    int numCols;

    boolean isValid(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    public int shortestPath(int[][] grid, int k) {
        numRows = grid.length;
        numCols = grid[0].length;
        if (k >= numRows + numCols - 2) //manhattan distance
            return numRows + numCols - 2;

        List<Integer> initState = Arrays.asList(0, 0, k); //x,y,num_remaining_quota
        Map<List<Integer>, Integer> stateToDist = new HashMap<>(); //state -> steps from origin
        stateToDist.put(initState, 0);

        Queue<List<Integer>> states = new ArrayDeque<>();
        states.offer(initState);

        Set<List<Integer>> visited = new HashSet<>();
        visited.add(initState);

        while (!states.isEmpty()) {
            List<Integer> curr = states.poll();
            for (int i = 0; i < 4; ++i) {
                int x = curr.get(0) + dx[i];
                int y = curr.get(1) + dy[i];
                if (isValid(x, y)) {
                    if (x == numRows - 1 && y == numCols - 1) //reached dest
                        return stateToDist.get(curr) + 1;
                    int numRemQuota = curr.get(2) - grid[x][y]; //if not obstacle, nothing deducted
                    List<Integer> nextState = Arrays.asList(x, y, numRemQuota);
                    if (numRemQuota >= 0 && !visited.contains(nextState)) {
                        visited.add(curr);
                        states.offer(nextState);
                        stateToDist.put(nextState, stateToDist.get(curr) + 1);
                    }
                }
            }
        }
        return -1;
    }
}
