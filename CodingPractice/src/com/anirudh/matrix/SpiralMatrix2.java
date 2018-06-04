package com.anirudh.matrix;

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

    boolean[][] visited;
    int[][] matrix;
    int lastNum;

    public void getMat(int left, int right, int up, int down, int num) {
        if (num > lastNum)
            return;

        for (int i = left; i <= right; ++i) {//top row
            if (!visited[up][i]) {
                matrix[up][i] = num++;
                visited[up][i] = true;
            }
        }
        for (int i = up; i <= down; ++i) {//right column
            if (!visited[i][right]) {
                matrix[i][right] = num++;
                visited[i][right] = true;
            }
        }
        for (int i = right; i >= left; --i) {//bottom row
            if (!visited[down][i]) {
                matrix[down][i] = num++;
                visited[down][i] = true;
            }
        }
        for (int i = down; i >= up; --i) {//left column
            if (!visited[i][left]) {
                matrix[i][left] = num++;
                visited[i][left] = true;
            }
        }

        getMat(left + 1, right - 1, up + 1, down - 1, num);
    }

    public int[][] generateMatrix(int n) {
        if (n == 0)
            return new int[0][0];
        visited = new boolean[n][n];
        matrix = new int[n][n];

        lastNum = (int) Math.pow(n, 2);
        getMat(0, n - 1, 0, n - 1, 1);
        return matrix;
    }

}
