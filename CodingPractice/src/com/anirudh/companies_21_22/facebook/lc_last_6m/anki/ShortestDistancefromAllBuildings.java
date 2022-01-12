package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

/*
317. Shortest Distance from All Buildings
Hard

1375

123

Add to List

Share
You are given an m x n grid grid of values 0, 1, or 2, where:

each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.



Example 1:


Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
Example 2:

Input: grid = [[1,0]]
Output: 1
Example 3:

Input: grid = [[1]]
Output: -1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0, 1, or 2.
There will be at least one building in the grid.
Accepted
122,228
Submissions
279,990
 */

import java.util.*;

/*
- Do sequential BFS from all buildings (as need visited set for each) and find shortest distance of each cell from each building
    - Sum them all as you traverse
    - OPTIMIZATION: Create a new cumulative visited matrix. Increment cell everytime a building reaches it. For the
    BFS of the next building, use a filter of num_visited from the previous traversal
    - This way, only those cells vsisted in the last building traversal will be visited in this traversal, so on and so forth
- To make sure a valid cell can be reached by all buildings, make a Map {coordinate -> #buildings}
-- Only when #buildings is total buildings for a cell is it a valid cell
1. convert all obstacles to -2 to not interrupt in counting distances, all buildings to -1
2. count all buildings
3. Do sequential BFS from all buildings (as need visited set for each) and find shortest distance of each cell from each building and sum them up
    Create Map {coordinate -> Set<visited>} so that each building has its own visited map
    To make sure a valid cell can be reached by all buildings, make a Map {coordinate -> #buildings}
4. Go through matrix, if the cell is a valid cell that can be reached by all buildings, it is a contender for min distance
 */
public class ShortestDistancefromAllBuildings {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int[][] grid;

    boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    boolean isNotBuildOrObs(int x, int y) {
        return grid[x][y] != -2 && grid[x][y] != -1;
    }

    public int shortestDistance(int[][] grid) {
        int totBuildings = 0;
        Queue<List<Integer>> buildingsQ = new ArrayDeque<>(); //[i,j,dist]
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 2)
                    grid[i][j] = -2; //to not interrupt counting
                else if (grid[i][j] == 1) {
                    grid[i][j] = -1; //to not interrupt counting
                    totBuildings++;
                    buildingsQ.offer(Arrays.asList(i, j, 0));
                }
            }
        }
        this.grid = grid; //initialize after the changes above

        int[][] cumulativeVisited = new int[grid.length][grid[0].length];
        int currVisitedVal = -1;

        while (!buildingsQ.isEmpty()) {
            List<Integer> building = buildingsQ.poll();

            Queue<List<Integer>> q = new ArrayDeque<>();
            q.offer(building); //has x,y,dist

            currVisitedVal++;

            while (!q.isEmpty()) {
                List<Integer> cell = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = cell.get(0) + dx[i];
                    int y = cell.get(1) + dy[i];
                    if (isValid(x, y) && isNotBuildOrObs(x, y) && cumulativeVisited[x][y] == currVisitedVal) { //valid, not a building, not an obstacle, not visited
                        cumulativeVisited[x][y]++; //mark visited before putting in queue!
                        int dist = cell.get(2) + 1;
                        q.offer(Arrays.asList(x, y, dist)); //add to queue with new dist
                        grid[x][y] += dist; //add distance from current building

                    }
                }
            }
        }

        //grid is populated with sum of distances. Now get max while making sure the cell can reached by all buildings
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (isNotBuildOrObs(i, j) && cumulativeVisited[i][j] == totBuildings) {
                    res = Math.min(res, grid[i][j]);
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
