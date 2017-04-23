package com.anirudh.general_algos;

/**
 * Created by paanir on 4/22/17.
 */

//LeetCode#74. Under-construction
public class Search2DMatrix {
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

    public boolean matSearch(int row, int col) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length)
            return false;
        if(track[row] == 0){
            track[row] = 1;
        }
        else
            return false; //already checked this row.
        int curr = matrix[row][col];
        if (curr == target)
            return true;
        if ( target < curr) {
            if (target >= matrix[row][0]) {
                currRow = matrix[row];
                System.out.println(row);
                return binSearch(0, col - 1);
            } else{
                if(row == 0)
                    return false;
                return matSearch((row - 1) / 2, col / 2);
            }

        } else { // target > curr
            if (target <= matrix[row][matrix[0].length - 1]) {
                currRow = matrix[row];
                System.out.println(row);
                return binSearch(col + 1, matrix[0].length - 1);
            } else {
                return matSearch((row + 1 + matrix.length) / 2, col / 2);
            }
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        this.matrix = matrix;
        this.target = target;
        this.track = new int[matrix.length];
        return matSearch(matrix.length / 2, matrix[0].length / 2);
    }

    public static void main(String[] args) {
        Search2DMatrix s = new Search2DMatrix();
        int[][] mat = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target = 3;
        System.out.println(s.searchMatrix(mat, target));
    }
}
