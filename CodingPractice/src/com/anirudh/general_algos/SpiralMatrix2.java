package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/21/17.
 */
/*
59. Spiral Matrix II
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
public class SpiralMatrix2 {

    int[][] track;
    int[][] matrix;
    int last;

    public void getMat(int left, int right, int up, int down, int num) {
        if (num > last)
            return;

        for (int i = left; i <= right; ++i) {//top row
            if (track[up][i] == 0) {
                matrix[up][i] = num++;
                track[up][i] = 1;
            }
        }
        for (int i = up; i <= down; ++i) {//right column
            if (track[i][right] == 0) {
                matrix[i][right] = num++;
                track[i][right] = 1;
            }
        }
        for (int i = right; i >= left; --i) {//bottom row
            if (track[down][i] == 0) {
                matrix[down][i] = num++;
                track[down][i] = 1;
            }
        }
        for (int i = down; i >= up; --i) {//left column
            if (track[i][left] == 0) {
                matrix[i][left] = num++;
                track[i][left] = 1;
            }
        }

        getMat(left + 1, right - 1, up + 1, down - 1, num);
    }

    public int[][] generateMatrix(int n) {
        if (n == 0)
            return new int[0][0];
        track = new int[n][n];
        matrix = new int[n][n];

        last = (int) Math.pow(n, 2);
        getMat(0, n - 1, 0, n - 1, 1);
        return matrix;
    }

}
