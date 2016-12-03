package com.anirudh.general_algos.packaged_problems.setmatrixzero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 13/9/16.
 */

//Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place

public class SetMatrixZeroes {

    int[][] matrix = new int[][]{
            {1,2,8,5,1},
            {3,0,2,2,1},
            {5,0,1,1,0},
            {2,2,8,3,1},
            {9,1,0,2,2},
            {8,9,5,5,4}
    };

    int numberOfRows =matrix.length;
    int numberOfCols = matrix[0].length;

    //Space: O(m + n) = O(n) as used 2 aux arrays to store indices
    //Time: O(mn + mn + mn) = O(n^2)
    public void setZeroes() {
        //saving all x and y indices that will have 0s later
        List<Integer>xIndices = new ArrayList<Integer>();
        List<Integer>yIndices = new ArrayList<Integer>();
        int i, j, rowNumber, colNumber;
        for(i = 0; i<numberOfRows; i++){
            for(j = 0; j<numberOfCols; j++){
                if(matrix[i][j] == 0){
                    xIndices.add(i);
                    yIndices.add(j);
                }
            }
        }
        //iterating through xIndices table and zero-ing corresponding matrix elements
        for(i = 0; i<xIndices.size(); i++){
            rowNumber = xIndices.get(i);
            for(j = 0; j<numberOfCols; j++){
                matrix[rowNumber][j] = 0;
            }
        }
        //iterating through yIndices table and zero-ing corresponding matrix elements
        for(j = 0; j<yIndices.size(); j++){
            colNumber = yIndices.get(j);
            for(i = 0; i<numberOfRows; i++){
                matrix[i][colNumber] = 0;
            }
        }
    }

    //Assuming matrix only has positive elements, as will be using -999 as sentinels
    public void SetZeroesInPlace(){
        int i, j, ii, jj;
        //Converting all elements in row and column of the explored 0 to -999 except the 0 itself (or any other zeroes
        // in that row and column)
        for(i = 0; i<numberOfRows; i++){
            for(j = 0; j<numberOfCols; j++){
                if(matrix[i][j] == 0){
                    for(jj = 0; jj< numberOfCols; jj++){
                        if(matrix[i][jj] !=0)
                            matrix[i][jj] = -999;
                    }
                    for(ii = 0; ii< numberOfRows; ii++){
                        if(matrix[ii][j] !=0)
                            matrix[ii][j] = -999;
                    }
                }
            }
        }
        //Converting all -999s to 0s
        for(i = 0; i<numberOfRows; i++){
            for(j = 0; j<numberOfCols; j++){
                if(matrix[i][j] == -999){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void print(){
        int i, j;
        for(i = 0; i<numberOfRows; i++){
            for(j = 0; j<numberOfCols; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
