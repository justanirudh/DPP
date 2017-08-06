package com.anirudh.datastructures.graphs;

import java.util.ArrayList;

/**
 * Created by paanir on 3/28/17.
 */
//IMP: DFS in a matrix!

/*
Black Shapes
Given N * M field of O's and X's, where O=white, X=black
Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)

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
public class BlackShapes {
    private boolean marked[][]; //explored
    private int di[] = new int[]{1, -1, 0, 0}; //neighbours: (1,0) (-1, 0) (0, 1) (0,-1)
    private int dj[] = new int[]{0, 0, 1, -1};//neighbours ^
    private ArrayList<String> A;
    int m, n;

    public boolean isValid(int i, int j) {

        if (i < 0 || i >= m || j < 0 || j >= n) //are in the matrix
            return false;

        if (A.get(i).charAt(j) != 'X') //is X, word at i and char at j
            return false;

        return true;
    }

    public void dfs(int i, int j) {

        marked[i][j] = true; //marked explored in a new matrix! no need waste time and make an adjacency list!!
        //also, no need to turn it to black, etc. Discovered and Finished are the same state here (unexplored, discovered, finished)

        for (int k = 0; k < 4; k++) { //for each neighbour, (creating the neighbour coordinates)

            int ii = i + di[k];
            int jj = j + dj[k];

            //(ii, jj) is a neighbour
            if (isValid(ii, jj) && !marked[ii][jj]) { //is valid and white
                dfs(ii, jj);
            }
        }
    }

    public int black(ArrayList<String> A) { //find number of cliques, but using dfs in a matrix
        int count;

        if (A == null)
            return 0;

        m = A.size();
        n = A.get(0).length();

        marked = new boolean[m][n];
        count = 0;
        this.A = A;

        for (int i = 0; i < m; i++) { //dfs helper function
            for (int j = 0; j < n; j++) {
                if (!marked[i][j] && A.get(i).charAt(j) == 'X') { //if white, do dfs
                    dfs(i, j);
                    count++; //number of trees
                }
            }
        }

        return count;
    }

}
