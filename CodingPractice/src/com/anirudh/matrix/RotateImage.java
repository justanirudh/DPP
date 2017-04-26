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


    //O(1) space; O(n^2) time
    int[][] matrix;

    public void rotateCW(int up, int down, int left, int right){
        if(up > down || left > right)
            return;

        int d = down;
        int l = left;
        int r = right;
        int u = up;

        while(l < right){ //for all except the last element
            int temp = matrix[up][l];
            matrix[up][l] = matrix[d][left];
            matrix[d][left] = matrix[down][r];
            matrix[down][r] = matrix[u][right];
            matrix[u][right] = temp;
            --d;
            --r;
            ++u;
            ++l;
        }
        rotateCW(up+1, down-1, left + 1, right - 1); //get into inner matrix
    }
    public void rotate(int[][] matrix) {
        this.matrix = matrix;
        rotateCW(0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    //----------------------------------------------
    //O(n^2) space; O(n^2) time
    public void rotateNaive(int[][] matrix) {
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
