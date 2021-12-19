package com.anirudh.companies_21_22.google.lc_last_6m.anki;

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

/*
For each row and each col, create 2 arrays: left and right
then for each cell, find min (leftmax, rightmax, top-max, bottom-max) - height_cell
 */
public class TrappingRainwaterII {
  public int trapRainWater(int[][] heightMap) {
    int[][] lToR = new int[heightMap.length][heightMap[0].length];
    int[][] rToL = new int[heightMap.length][heightMap[0].length];
    int[][] topToBottom = new int[heightMap.length][heightMap[0].length];
    int[][] bottomToTop = new int[heightMap.length][heightMap[0].length];

    for (int row = 0; row < heightMap.length; ++row) {// l to r
      for (int col = 0; col < heightMap[0].length; ++col) {
        if (col == 0) //first column
        {
          lToR[row][col] = heightMap[row][col];
        } else {
          lToR[row][col] = Math.max(lToR[row][col - 1], heightMap[row][col]);
        }
      }
    }

    for (int row = 0; row < heightMap.length; ++row) { // r to l
      for (int col = heightMap[0].length - 1; col >= 0; --col) {
        if (col == heightMap[0].length - 1) //last column
        {
          rToL[row][col] = heightMap[row][col];
        } else {
          rToL[row][col] = Math.max(rToL[row][col + 1], heightMap[row][col]);
        }
      }
    }

    for (int col = 0; col < heightMap[0].length; ++col) { // top to bottom
      for (int row = 0; row < heightMap.length; ++row) {
        if (row == 0) { //first row
          topToBottom[row][col] = heightMap[row][col];
        } else {
          topToBottom[row][col] = Math.max(topToBottom[row - 1][col], heightMap[row][col]);
        }
      }
    }

    for (int col = 0; col < heightMap[0].length; ++col) { // bottom to top
      for (int row = heightMap.length - 1; row >= 0; --row) {
        if (row == heightMap.length - 1) { //last row
          bottomToTop[row][col] = heightMap[row][col];
        } else {
          bottomToTop[row][col] = Math.max(bottomToTop[row + 1][col], heightMap[row][col]);
        }
      }
    }

    //find trapped rain water
    int sum = 0;
    for (int row = 0; row < heightMap.length; ++row) {
      for (int col = 0; col < heightMap[0].length; ++col) {
        sum += Math.min(Math.min(lToR[row][col], rToL[row][col]), Math.min(topToBottom[row][col],
            bottomToTop[row][col])) - heightMap[row][col];
      }
    }
    return sum;
  }
}
