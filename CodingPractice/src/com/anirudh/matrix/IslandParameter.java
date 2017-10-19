package com.anirudh.matrix;

/**
 * Created by paanir on 10/19/17.
 */
/*
463. Island Perimeter
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 and there is exactly one island (i.e., one or more connected land cells). The island doesn't have
 "lakes" (water inside that isn't connected to the water around the island). One cell is a square
 with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter
 of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class IslandParameter {
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        //0 to 1 and 1 to 0 changes per row
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length - 1; ++j) {
                if (grid[i][j] != grid[i][j + 1])
                    count++;
            }
        }
        //0 to 1 and 1 to 0 changes per col
        for (int j = 0; j < grid[0].length; ++j) {
            for (int i = 0; i < grid.length - 1; ++i) {
                if (grid[i][j] != grid[i + 1][j])
                    count++;
            }
        }
        //all in boundaries
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1 && (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1)) {
                    int bounds = 0;
                    if (i == 0)
                        bounds++;
                    if (i == grid.length - 1)
                        bounds++;
                    if (j == 0)
                        bounds++;
                    if (j == grid[0].length - 1)
                        bounds++;
                    count = count + bounds;
                }

            }
        }
        return count;
    }
}
