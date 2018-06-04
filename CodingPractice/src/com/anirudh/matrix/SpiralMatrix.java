package com.anirudh.matrix;

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
    boolean[][] visited;
    int[][] matrix;

    public List<Integer> printMatrix(int left, int right, int up, int down, List<Integer> list) {
        if (left > right || up > down)
            return list;

        for (int i = left; i <= right; ++i) {//top row
            if (!visited[up][i]) {
                list.add(matrix[up][i]);
                visited[up][i] = true;
            }
        }
        for (int i = up; i <= down; ++i) {//right column
            if (!visited[i][right]) {
                list.add(matrix[i][right]);
                visited[i][right] = true;
            }
        }
        for (int i = right; i >= left; --i) {//bottom row
            if (!visited[down][i]) {
                list.add(matrix[down][i]);
                visited[down][i] = true;
            }
        }
        for (int i = down; i >= up; --i) {//left column
            if (!visited[i][left]) {
                list.add(matrix[i][left]);
                visited[i][left] = true;
            }
        }

        return printMatrix(left + 1, right - 1, up + 1, down - 1, list);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<>();
        visited = new boolean[matrix.length][matrix[0].length];
        this.matrix = matrix;
        return printMatrix(0, matrix[0].length - 1, 0, matrix.length - 1, new ArrayList<>());
    }
}
