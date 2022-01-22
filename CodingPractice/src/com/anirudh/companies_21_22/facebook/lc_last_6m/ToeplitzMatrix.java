package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
766. Toeplitz Matrix
Easy

1769

113

Add to List

Share
Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.



Example 1:


Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
Example 2:


Input: matrix = [[1,2],[2,2]]
Output: false
Explanation:
The diagonal "[1, 2]" has different elements.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99
 */

import java.util.HashMap;
import java.util.Map;

/*
For elements in same top-left to bottom-right diagonal
    row-col is same
For elements in same bottom-left to top-right diagonal
    row-col is same
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (!values.containsKey(i - j)) {
                    values.put(i - j, matrix[i][j]);
                } else {
                    if (matrix[i][j] != values.get(i - j))
                        return false;
                }
            }
        }
        return true;
    }
}
