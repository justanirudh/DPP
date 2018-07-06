package com.anirudh.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paanir on 5/2/17.
 */
/*
36. Valid Sudoku
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        //checking rows and columns
        for (int i = 0; i < 9; ++i) {
            Set<Character> hsR = new HashSet<>();
            Set<Character> hsC = new HashSet<>();
            for (int j = 0; j < 9; ++j) {
                //per row
                char currR = board[i][j];
                if (currR != '.') {
                    if (hsR.contains(currR))
                        return false;
                    hsR.add(currR);
                }
                //per col
                char currC = board[j][i];
                if (currC != '.') {
                    if (hsC.contains(currC))
                        return false;
                    hsC.add(currC);
                }
            }
        }

        //checking per 3x3 box
        int r = 0, c = 0;
        while (r != 9) {
            while (c != 9) {
                HashSet<Character> hs = new HashSet<>();
                for (int i = r; i < r + 3; ++i) {
                    for (int j = c; j < c + 3; ++j) {
                        char curr = board[i][j];
                        if (curr != '.') {
                            if (hs.contains(curr))
                                return false;
                            hs.add(curr);
                        }
                    }
                }
                c += 3;
            }
            c = 0;
            r += 3;
        }

        return true;
    }
}
