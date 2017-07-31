package com.anirudh.matrix;

/**
 * Created by paanir on 4/22/17.
 */

/*
74. Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 */
//O(log(mn))
public class Search2DMatrix {

    //SOLUTION 2: Treat it as a sorted array, not a matrix!! O(log(m+n))
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0;
        int end = m * n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int midX = mid / n; //num rows
            int midY = mid % n; //num cols

            if (matrix[midX][midY] == target)
                return true;

            if (matrix[midX][midY] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    //------------------------------------------------------

    int[][] matrix;
    int target;
    int[] currRow;
    int[] track;

    public boolean binSearch(int startCol, int endCol) {
        if (startCol > endCol)
            return false;
        int curr = currRow[(startCol + endCol) / 2];
        if (curr == target)
            return true;
        else if (target > curr) {
            return binSearch(startCol + 1, endCol);
        } else
            return binSearch(startCol, endCol - 1);
    }

    public boolean matSearch(int start, int end) {
        int row = (start + end) / 2;
        int col = matrix[0].length / 2;
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length)
            return false;
        if (track[row] == 0) {
            track[row] = 1;
        } else
            return false; //already checked this row.
        int curr = matrix[row][col];
        if (curr == target)
            return true;
        if (target < curr) {
            if (target >= matrix[row][0]) {
                currRow = matrix[row];
                return binSearch(0, col - 1);
            } else {
                if (row == 0)
                    return false;
                return matSearch(start, row - 1);
            }

        } else { // target > curr
            if (target <= matrix[row][matrix[0].length - 1]) {
                currRow = matrix[row];
                return binSearch(col + 1, matrix[0].length - 1);
            } else {
                return matSearch(row + 1, end);
            }
        }
    }

    public boolean searchMatrixSlow(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        this.matrix = matrix;
        this.target = target;
        this.track = new int[matrix.length];
        return matSearch(0, matrix.length);
    }

    public static void main(String[] args) {
        Search2DMatrix s = new Search2DMatrix();
        int[][] mat = {{-10, -8}, {-6, -5}, {-2, -2}, {-1, 0}, {3, 4}, {7, 7}, {8, 9}, {10, 10}, {11, 11}, {12, 14},
                {15, 16}, {17, 19}, {20, 21}, {22, 22}, {25, 27}, {28, 30}, {32, 32}, {35, 36}};
        int target = 16;
        System.out.println(s.searchMatrix(mat, target));
    }
}
