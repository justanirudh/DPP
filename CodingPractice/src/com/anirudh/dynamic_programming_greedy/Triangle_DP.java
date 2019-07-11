package com.anirudh.dynamic_programming_greedy;

import java.util.*;

/**
 * Created by anirudh on 27/11/16.
 */
/*
120. LeetCode – Triangle_DP (Java) (Like 64. MinimumPathSum)

Given a triangle, find the minimum path sum from top to bottom.
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Triangle_DP {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty() || triangle.get(0).isEmpty())
            return 0;
        if (triangle.size() == 1)
            return triangle.get(0).get(0);

        int triangleBaseLength = triangle.get(triangle.size() - 1).size();
        int[][] sums = new int[triangleBaseLength][triangleBaseLength]; //creating max-sums matrix //min path can come from top or top left in this
        sums[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); ++i) { //0th row with 1 element already populated as base case
            for (int j = 0; j < triangle.get(i).size(); ++j) { //go on;y till end of that row
                int top = (j != triangle.get(i).size() - 1) ? sums[i - 1][j] : Integer.MAX_VALUE; //last element of row has no top
                int topLeft = (j != 0) ? sums[i - 1][j - 1] : Integer.MAX_VALUE; //first element of row has no topLeft
                sums[i][j] = triangle.get(i).get(j) + Math.min(top, topLeft);
            }
        }

        //check all elements of sums last row. return min of them. last row because it is the base of the triangle
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangleBaseLength; ++i) {
            if (sums[triangleBaseLength - 1][i] < min)
                min = sums[triangleBaseLength - 1][i];
        }
        return min;
    }
}
