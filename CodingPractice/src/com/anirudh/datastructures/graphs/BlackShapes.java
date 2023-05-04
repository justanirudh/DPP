package com.anirudh.datastructures.graphs;

import java.util.List;

/**
 * Created by paanir on 3/28/17.
 */
//IMP: DFS in a matrix!

/*
Black Shapes
Given N * M field of O's and X's, where O=white, X=black
Return the number of black shapes. input black shape consists of one or more adjacent X's (diagonals not included)

Example:

OOOXOOO
OOXXOXO
OXOOOXO

answer is 3 shapes are  :
(i)    X
     X X
(ii)
      X
 (iii)
      X
      X
Note that we are looking for connected shapes here.

For example,

XXX
XXX
XXX
is just one single connected black shape.
 */

/**
 * DFS in a matrix
 */
public class BlackShapes {
    private boolean[][] visited; //explored
    private final int[] di = new int[]{1, -1, 0, 0}; //neighbours: (1,0) (-1, 0) (0, 1) (0,-1)
    private final int[] dj = new int[]{0, 0, 1, -1};//neighbours ^
    private List<String> input;
    private int nRows, nCols;

    private boolean isValid(int i, int j) {

        if (i < 0 || i >= nRows || j < 0 || j >= nCols) //are in the matrix
            return false;

        //is X, word at i and char at j
        return input.get(i).charAt(j) == 'X';
    }

    private void dfs(int i, int j) {

        visited[i][j] = true; //discovered explored in a new matrix! no need waste time and make an adjacency list!!
        //also, no need to turn it to black, etc. Discovered and Finished are the same state here (unexplored, discovered, finished)

        for (int k = 0; k < 4; k++) { //for each neighbour, (creating the neighbour coordinates)

            int ii = i + di[k];
            int jj = j + dj[k];

            //(ii, jj) is a neighbour
            if (isValid(ii, jj) && !visited[ii][jj]) { //is valid and "white"/undiscovered
                dfs(ii, jj);
            }
        }
    }

    public int black(List<String> input) { //find number of connected components, by using dfs in a matrix (dfs covers whole of a disconnected graph)
        if (input == null)
            return 0;

        nRows = input.size();
        nCols = input.get(0).length();

        visited = new boolean[nRows][nCols];
        int res = 0;
        this.input = input;

        for (int i = 0; i < nRows; i++) { //dfs helper function
            for (int j = 0; j < nCols; j++) {
                if (input.get(i).charAt(j) == 'X' && !visited[i][j]) { //if black, do dfs
                    dfs(i, j);
                    res++; //number of trees
                }
            }
        }

        return res;
    }

}
