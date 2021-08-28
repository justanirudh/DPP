package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

/**
 * Created by paanir on 8/27/21.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 * Medium
 * <p>
 * 675
 * <p>
 * 83
 * <p>
 * Add to List
 * <p>
 * Share
 * (This problem is an interactive problem.)
 * <p>
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 * <p>
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 * <p>
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 * <p>
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 * Example 4:
 * <p>
 * <p>
 * <p>
 * Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 */

/**
 * // my solution: Do Binary search. if col is not empty, check zeroes on left column before proceeding left

 //LC solution [Better, nlogm]: do binary search in each row to find leftmost 1 and record their indices. Then find the minimum of those indices
 //LC solution [n + m]: start from top right corner. if 0, go down, if 1, go left
 // https://leetcode.com/problems/leftmost-column-with-at-least-a-one/solution/
 */

public class LeftmostColumnwithatLeastaOne {

    //Given class--------------------------------start
    class BinaryMatrix {
        public int get(int row, int col) {
            return 0;
        }

        public List<Integer> dimensions() {
            return null;
        }
    }

    //Given class--------------------------------end

    boolean isLeftAllZeroes(BinaryMatrix  binaryMatrix, int col, List<Integer> rowsWithOnes) {

        for(int row: rowsWithOnes){
            if(binaryMatrix.get(row, col) == 1)
                return false;
        }
        return true;
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dims = binaryMatrix.dimensions();
        int numRows = dims.get(0);
        int numCols = dims.get(1);
        int s = 0;
        int e = numCols - 1;
        List<Integer> rowsWithOnes = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            rowsWithOnes.add(i);
        }
        while (s <= e) {
            int mid = s + (e - s) / 2; //binary search
            List<Integer> newRowsWithOnes = new ArrayList<>();
            for (int row : rowsWithOnes) {
                if (binaryMatrix.get(row, mid) == 1) {
                    newRowsWithOnes.add(row);
                }
            }
            if (newRowsWithOnes.isEmpty()) { //if going towards right, keep using original rows
                s = mid + 1;
            } else {
                if(mid == 0)
                    return 0;
                //check col-1 has all zeroes?
                boolean leftAllZeroes = isLeftAllZeroes(binaryMatrix, mid-1, newRowsWithOnes);
                if(leftAllZeroes)
                    return mid;
                e = mid - 1;
                rowsWithOnes = newRowsWithOnes; //decreasing number of rows to search as we go
            }
        }
        return -1;
    }
}
