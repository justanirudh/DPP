package com.anirudh.matrix;

/**
 * Created by paanir on 4/24/17.
 */
/*
48. Rotate Image
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
 */
public class RotateImage {

    //O(n^2) space; O(n^2) time
    public void rotate(int[][] matrix) {
        int numRows =  matrix.length;
        int numCols = matrix[0].length;
        int [][] newMatrix = new int[numCols][numRows];
        int nR = 0;
        int nC = numCols - 1;
        //make new matrix
        for(int r = 0; r < numRows; ++r){
            for(int c = 0; c < numCols; ++c){
                newMatrix[nR][nC] = matrix[r][c];
                ++nR;
            }
            nR = 0;
            --nC;
        }
        //copy to old matrix
        for(int r = 0; r < numRows; ++r){
            for(int c = 0; c < numCols; ++c){
                matrix[r][c] = newMatrix[r][c];
            }
        }
    }


}
