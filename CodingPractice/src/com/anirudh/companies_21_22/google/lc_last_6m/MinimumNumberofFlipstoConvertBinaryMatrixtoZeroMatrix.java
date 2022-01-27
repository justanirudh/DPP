package com.anirudh.companies_21_22.google.lc_last_6m;

/*
1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
Hard

470

48

Add to List

Share
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

A binary matrix is a matrix with all cells equal to 0 or 1 only.

A zero matrix is a matrix with all cells equal to 0.



Example 1:


Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We do not need to change it.
Example 3:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix cannot be a zero matrix.


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 3
mat[i][j] is either 0 or 1.
Accepted
15,731
Submissions
22,158
 */

import java.util.*;

/*
Do Brute Force using BFS
All matrices M formed by all flips from current matrix m will be m's neighbours;
To put them in a set, create a hash with all vals in a matrix appended

Each flip is a new state; Use visited set to not have repeats
Tx (??):  2^mn OR {mn * (2^mn); for each cell (m*n) we are traversing through all possible combinations to get Neighbours (2^mn)}
Sx: 2^mn: as we are making 1 matrix for each combination
 */
public class MinimumNumberofFlipstoConvertBinaryMatrixtoZeroMatrix {

    static class State {
        int[][] mat;
        int steps;
        boolean isZeroMatrix;
        String hash;

        State(int[][] mat, int steps) {
            this.mat = mat;
            this.steps = steps;

            isZeroMatrix = true;
            for (int i = 0; i < mat.length; ++i) { //is zeroMatrix
                for (int j = 0; j < mat[0].length; ++j) {
                    if (mat[i][j] != 0) {
                        isZeroMatrix = false;
                        break;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mat.length; ++i) { // get hash; basically append all values
                for (int j = 0; j < mat[0].length; ++j) {
                    sb.append(mat[i][j]);
                }
            }
            hash = sb.toString();
        }

        private State flip(int x, int y) { //O(mn)
            int[][] next = new int[mat.length][mat[0].length];
            for (int i = 0; i < mat.length; ++i) {
                for (int j = 0; j < mat[0].length; ++j) {
                    if ((i == x && j == y) || //same cell or neighbour
                            (i == x - 1 && j == y) ||
                            (i == x && j == y - 1) ||
                            (i == x + 1 && j == y) ||
                            (i == x && j == y + 1)) {
                        next[i][j] = (mat[i][j] == 1) ? 0 : 1;
                    } else {
                        next[i][j] = mat[i][j];
                    }
                }
            }
            return new State(next, steps + 1);
        }

        List<State> getNeighbours() { //flip each cell and its neighbours; O(m^2n^2) OR O(2^mn)
            List<State> list = new ArrayList<>();
            for (int i = 0; i < mat.length; ++i) {
                for (int j = 0; j < mat[0].length; ++j) {
                    list.add(flip(i, j));
                }
            }
            return list;
        }
    }

    public int minFlips(int[][] mat) {
        State s = new State(mat, 0);
        if(s.isZeroMatrix)
            return 0;
        Queue<State> q = new ArrayDeque<>();
        q.offer(s);

        Set<String> visited = new HashSet<>();
        visited.add(s.hash);

        while (!q.isEmpty()) { //BFS O(mn * 2^mn) maybe
            State curr = q.poll();
            List<State> neighbours = curr.getNeighbours();
            for (State next : neighbours) {
                String hash = next.hash;
                if (!visited.contains(hash)) {
                    if (next.isZeroMatrix) {
                        return next.steps;
                    }
                    visited.add(hash);
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}
