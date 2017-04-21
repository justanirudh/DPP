package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 4/20/17.
 */
/*
54. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {
    int[][] track;
    int[][] matrix;

    public List<Integer> printMat(int left, int right, int up, int down, List<Integer> list) {
        if (left > right || up > down)
            return list;

        for (int i = left; i <= right; ++i) {//top row
            if (track[up][i] == 0) {
                list.add(matrix[up][i]);
                track[up][i] = 1;
            }
        }
        for (int i = up; i <= down; ++i) {//right column
            if (track[i][right] == 0) {
                list.add(matrix[i][right]);
                track[i][right] = 1;
            }
        }
        for (int i = right; i >= left; --i) {//bottom row
            if (track[down][i] == 0) {
                list.add(matrix[down][i]);
                track[down][i] = 1;
            }
        }
        for (int i = down; i >= up; --i) {//left column
            if (track[i][left] == 0) {
                list.add(matrix[i][left]);
                track[i][left] = 1;
            }
        }

        return printMat(left + 1, right - 1, up + 1, down - 1, list);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<>();
        track = new int[matrix.length][matrix[0].length];
        this.matrix = matrix;
        return printMat(0, matrix[0].length - 1, 0, matrix.length - 1, new ArrayList<>());
    }
}
