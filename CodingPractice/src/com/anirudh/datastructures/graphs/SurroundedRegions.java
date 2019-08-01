package com.anirudh.datastructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//BFS, can be done by DFS too

/**
 * Created by paanir on 3/27/17.
 */
/*
130. Surrounded Regions
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/
public class SurroundedRegions {

    /*
1. traverse through all graphs that start in the border //DFS to go through all graphs
2. Populate the visited array
3. iterate through the graph and convert all the non-visited Os (graphs of 0s that were not in the border) to X
 */

    private boolean[][] visited;
    private char[][] board;
    private int numRows;
    private int numCols;
    private int[] dx;
    private int[] dy;


    private boolean isBorderO(int i, int j) {
        return board[i][j] == 'O' && (i == 0 || i == numRows - 1 || j == 0 || j == numCols - 1);
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < numRows && j >= 0 && j < numCols;
    }

    private void doDFS(int i, int j) {
        visited[i][j] = true;
        /*
        Look at all 4 neighbours and keep converting to #s until we are donewith the DFS
         */
        for (int k = 0; k < 4; ++k) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (isValid(x, y) && board[x][y] == 'O' && !visited[x][y]) {
                doDFS(x, y);
            }
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        numRows = board.length;
        numCols = board[0].length;
        this.board = board;
        dx = new int[]{0, 0, 1, -1};
        dy = new int[]{1, -1, 0, 0};

        visited = new boolean[numRows][numCols]; //all false

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (isBorderO(i, j)) {
                    doDFS(i, j);
                }
            }
        }

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (board[i][j] == 'O' && !visited[i][j])
                    board[i][j] = 'X';
            }
        }
    }

    public static void main(String[] args) {
        char[][] g = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new SurroundedRegions().solve(g);
    }
}
