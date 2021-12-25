package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

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
Normally, for BFS in a matrix we use 3 vars: x, y, distance from source
Here we will use 4 vars: x,y,dist, remaining quota of obstacles
first check if quota > manhattan distance. if so, return true
every time we go through an obstacle, decrease it by 1, check if it has been visited, then offer it in queue
EVERYTHING else remains the same

For State's equals and hashcode, use x,y,num_obstacles (NOT distance)
 */
class ShortestPathGridObstaclesElimination {

    class State {
        int x;
        int y;
        int dist;
        int numRemObs;

        State(int x, int y, int dist, int remainingObstacles) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.numRemObs = remainingObstacles;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State))
                return false;
            State s = (State) o;
            return this.x == s.x &&
                    this.y == s.y && //NO dist in equals
                    this.numRemObs == s.numRemObs;
        }

        @Override
        public int hashCode() {

            int a = 31 * this.x;
            a = 31 * a + this.y;
            return 31 * a + this.numRemObs; //NO dist in hashcode
        }
    }

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

        State start = new State(0, 0, 0, k);
        Queue<State> states = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();
        states.offer(start);
        while (!states.isEmpty()) {
            State curr = states.poll();
            visited.add(curr);
            for (int i = 0; i < 4; ++i) {
                int x = curr.x + dx[i];
                int y = curr.y + dy[i];
                if (isValid(x, y)) {
                    if (x == numRows - 1 && y == numCols - 1)
                        return curr.dist + 1;
                    int numRemObs = curr.numRemObs - grid[x][y];
                    State nextState = new State(x, y, curr.dist + 1, numRemObs);
                    if (numRemObs >= 0 && !visited.contains(nextState))
                        states.offer(nextState);
                }
            }
        }
        return -1;
    }
}
