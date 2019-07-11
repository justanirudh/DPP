package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 6/27/17.
 */
/*
304. Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {

    //https://www.youtube.com/watch?v=PwDqpOMwg6U
    //We essentially store at [i,j] sum of rectangle from [0,0] to [i-1. j-1]
    public class NumMatrix {

        int[][] matrix;
        //saving sums from [0,0] to [i,j] for each [i,j] in matrix
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            if (matrix != null && matrix.length != 0) {
                this.matrix = matrix;
                int len = matrix.length;
                int width = matrix[0].length;
                sums = new int[len + 1][width + 1];
                for (int i = 1; i < len + 1; ++i) {
                    for (int j = 1; j < width + 1; ++j) {
                        //adding the elem itself, one row less in sums, one col less in sums then subtracting once for the twice counted at sums[i-1][j-1]
                        sums[i][j] = matrix[i - 1][j - 1] + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            //sum of
            //biggest square from 00 to row2 col2 (in sums matrix till r2 + 1, c2 + 1)
            //subtract the left rectangle
            //subtract the top rectangle
            //add the twice subtracted common rectangleon top left corner
            return sums[row2 + 1][col2 + 1] - sums[row2 + 1][col1] - sums[row1][col2 + 1] + sums[row1][col1];
        }
    }



    //---------------------------------------------------------

    public class NumMatrixNaive {

        int[][] matrix;

        public NumMatrixNaive(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int x = row1; x <= row2; ++x) {
                for (int y = col1; y <= col2; ++y) {
                    sum += matrix[x][y];
                }
            }
            return sum;
        }
    }


}
