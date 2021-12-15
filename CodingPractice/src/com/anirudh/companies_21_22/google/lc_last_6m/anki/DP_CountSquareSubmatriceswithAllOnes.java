package com.anirudh.companies_21_22.google.lc_last_6m.anki;

/*
1277. Count Square Submatrices with All Ones
Medium

2803

45

Add to List

Share
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.


Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
Accepted
141,352

 */
/*
Idea is to
1. find a way to get matrix sizes
2. memoize them

For each cell, if it is NOT in 1st row or 1st col:
    if(cell == 1)
        num_matrices with the cell as the bottom-right cell = Min(top, left, top-left) + 1
adding 1 as each 1 cell is in itself a solution
 */
public class DP_CountSquareSubmatriceswithAllOnes {
    public int countSquares(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (i == 0 || j == 0) {
                    sum += matrix[i][j]; //each 1 cell is in itself a solution
                } else if (matrix[i][j] == 1) {
                    matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1])) + 1;
                    sum += matrix[i][j];
                }
            }
        }
        return sum;
    }
}
