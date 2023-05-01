package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
311. Sparse Matrix Multiplication
Medium
961
336
company
Bloomberg
company
Apple
Databricks
Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.



Example 1:


Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]
Example 2:

Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]


Constraints:

m == mat1.length
k == mat1[i].length == mat2.length
n == mat2[i].length
1 <= m, n, k <= 100
-100 <= mat1[i][j], mat2[i][j] <= 100
 */

/*
Sx = xy +yz + xz; where x = num rows in mat 1, so on..
Tx = xz * num_nonempty

Make mat1m map: x -> {y -> val}
Make mat2m map: y -> {x -> val}
traverse through res matrix: i,j
if i in mat1m && j in ma2tm means there is atleast 1 non-null value in i row of mat1
and j column in mat2, meaning can give a non-null value for res[i][j]
Now get the nested maps for i and j: mat1mm and mat2mm,
if both have same key, sum += mat1mm(key) * mat2mm(key) because always:
1st col elem is multipled with 1st row elem, 2nd col elem * to 2nd row elem, so on...

 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        if (mat1.length == 0 || mat2.length == 0) {
            return new int[0][0];
        }
        Map<Integer, Map<Integer, Integer>> mat1m = new HashMap<>();
        for (int i = 0; i < mat1.length; ++i) {
            for (int j = 0; j < mat1[0].length; ++j) {
                if (mat1[i][j] != 0) {
                    mat1m.putIfAbsent(i, new HashMap<>());
                    mat1m.get(i).put(j, mat1[i][j]);
                }
            }
        }
        Map<Integer, Map<Integer, Integer>> mat2m = new HashMap<>();
        for (int i = 0; i < mat2.length; ++i) {
            for (int j = 0; j < mat2[0].length; ++j) {
                if (mat2[i][j] != 0) {
                    mat2m.putIfAbsent(j, new HashMap<>());
                    mat2m.get(j).put(i, mat2[i][j]);
                }
            }
        }
        int[][] res = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[0].length; ++j) {
                if (mat1m.containsKey(i) && mat2m.containsKey(j)) {
                    Map<Integer, Integer> mat1mm = mat1m.get(i);
                    Map<Integer, Integer> mat2mm = mat2m.get(j);
                    Map<Integer, Integer> smaller;
                    Map<Integer, Integer> larger;
                    if (mat1mm.size() > mat2mm.size()) {
                        smaller = mat2mm;
                        larger = mat1mm;
                    } else {
                        smaller = mat1mm;
                        larger = mat2mm;
                    }
                    int resij = 0;
                    for (int k : smaller.keySet()) {
                        if (larger.containsKey(k)) {
                            resij += smaller.get(k) * larger.get(k);
                        }
                    }
                    res[i][j] = resij;
                }
                // else default 0
            }
        }
        return res;

    }
}
