package com.anirudh.interview_prep_2021.two_sigma;

/*
289. Game of Life
Medium

3148

364

Add to List

Share
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 */

/*
Do it inplace
1 -> <2 1s -> 0
1 -> 2/3 1s -> 1
1 -> >3 1s -> 0
0 -> 3 1s -> 1

Since in-place:
if 0 -> 1 => 0 -> 2 (current state is 0, next state is 1)
if 1 -> 0 => 1 -> -2 (current state is 1, next state is 0)

 */
public class GameofLife {

    int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};
    int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};
    int[][] board;

    boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public void gameOfLife(int[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                //look at all 8 neighbours
                int alive = 0;
                for (int k = 0; k < 8; ++k) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (isValid(x, y)) {
                        if (board[x][y] == 1 || board[x][y] == -2)
                            alive++;
                    }
                }
                if (board[i][j] == 1 && alive < 2)
                    board[i][j] = -2;
                else if (board[i][j] == 1 && (alive == 2 || alive == 3)) {
                    //NOOP
                } else if (board[i][j] == 1 && alive > 3)
                    board[i][j] = -2;
                else if (board[i][j] == 0 && alive == 3)
                    board[i][j] = 2;
            }
        }
        //now matrix will have 0s, 1s, 2s and -2s
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 2)
                    board[i][j] = 1;
                else if (board[i][j] == -2)
                    board[i][j] = 0;
            }
        }
    }
}
