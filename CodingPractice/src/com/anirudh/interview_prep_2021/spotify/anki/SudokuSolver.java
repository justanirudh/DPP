package com.anirudh.interview_prep_2021.spotify.anki;

/**
 * Created by paanir on 8/27/17.
 */
/*
37. Sudoku Solver
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
A sudoku puzzle...
...and its solution numbers marked in red.
 */
//Complexity: Try 1 through 9 for each cell. The time complexity should be 9 ^ m (m represents the number of blanks to
// be filled in), since each blank can have 9 choices.
//DFS with backtracking
public class SudokuSolver {

    private char[][] board;

    //checking if the digit does not already exist in the same row or same column or same 3 X 3 matrix
    //below is fancy way of doing this. I can instead have 2-3 loops
    private boolean isValid(int row, int col, char c) {
        int x = 3 * (row / 3); //x start of 3x3 block
        int y = 3 * (col / 3); //y start of 3x3 block
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c)  //check column
                return false;
            if (board[row][i] == c) //check row
                return false;
            if (board[x + i / 3][y + i % 3] == c) //left to right
                return false;
        }
        return true;
    }

    private boolean solve() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) { //trial. Try 1 through 9
                        if (isValid(i, j, c)) {
                            board[i][j] = c; //Put c for this cell
                            if (solve())
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back and check by putting some other number
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        this.board = board;
        solve();
    }

}
