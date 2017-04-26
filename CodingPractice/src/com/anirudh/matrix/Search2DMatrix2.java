package com.anirudh.matrix;

/**
 * Created by paanir on 4/23/17.
 */
/*
240. Search a 2D Matrix II
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */
//O(m + n)
public class Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int numRows = matrix.length;
        if(numRows == 0)
            return false;
        int numCols = matrix[0].length;
        int r = 0;
        int c = numCols - 1;
        while(r < numRows && c >= 0){
            int curr = matrix[r][c]; //start with top right
            if(curr == target)
                return true;
            else if(target > curr)
                r++;
            else //target < curr
                c--;
        }
        return false;
    }
}
