package com.anirudh.general_algos.fluff;

/**
 * Created by paanir on 4/10/17.
 */
/*
289. Game of Life
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight
neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells
first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems
when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {
    //In place solution
    int[][] board;

    boolean legal(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    /*
    0 -> 0 10
    0 -> 1 20
    1 -> 0 30
    1 -> 1 40
    */
    int nextState(int x, int y) { //x = row, y = column

        int numLiveNeighbours = 0;
        int[] a1 = {x - 1, x - 1, x, x + 1, x + 1, x + 1, x, x - 1};
        int[] a2 = {y, y - 1, y - 1, y - 1, y, y + 1, y + 1, y + 1};
        for (int i = 0; i < a1.length; ++i) { //iterate through all neighbours
            int r = a1[i];
            int c = a2[i];
            if (legal(r, c) && (board[r][c] == 1 || board[r][c] == 30 || board[r][c] == 40)) //legal and live
                numLiveNeighbours++;
        }
        int curr = board[x][y];
        if (curr == 1) { //live
            if (numLiveNeighbours < 2)
                return 30; //1 -> 0
            if (numLiveNeighbours == 2 || numLiveNeighbours == 3)
                return 40; //1 -> 1
            if (numLiveNeighbours > 3)
                return 30; // 1 -> 0
        } else { //dead
            if (numLiveNeighbours == 3)
                return 20; //0 to 1
        }
        if (curr == 0)
            return 10; //0 -> 0
        else
            return 40; //1 -> 1
    }

    public void gameOfLife(int[][] board) {
        this.board = board;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = nextState(i, j);
            }
        }
        for (int i = 0; i < board.length; ++i) { //copying values
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 10 || board[i][j] == 30)
                    board[i][j] = 0;
                else
                    board[i][j] = 1;
            }
        }
    }
}
