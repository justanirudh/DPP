package com.anirudh.datastructures.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
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
 */
class ShortestPathGridObstaclesElimination {
    public int shortestPath(int[][] grid, int k) {
        return 0;
    }
}
