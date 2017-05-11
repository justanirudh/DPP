package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 5/12/17.
 */
/*
63. Unique Paths II
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rl = obstacleGrid.length;
        int cl = obstacleGrid[0].length;
        int[][] ways = new int [rl][cl];

        ways[0][0] = obstacleGrid[0][0]^1; //inverting 1 to 0

        for(int i = 1; i < cl; ++i)
            ways[0][i] = (obstacleGrid[0][i]^1) * (ways[0][i-1]);

        for(int i = 1; i < rl; ++i)
            ways[i][0] = (obstacleGrid[i][0]^1) * (ways[i-1][0]);

        for(int i = 1; i < rl; ++i){
            for(int j = 1; j < cl; ++j){
                ways[i][j] = (obstacleGrid[i][j]^1) * (ways[i-1][j] + ways[i][j-1]);
            }
        }

        return ways[rl-1][cl-1];
    }
}
