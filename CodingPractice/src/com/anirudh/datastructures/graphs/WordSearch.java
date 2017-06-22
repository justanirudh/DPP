package com.anirudh.datastructures.graphs;

/**
 * Created by paanir on 6/21/17.
 */
//DFS in matrix
/*
79. Word Search

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or
vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */
public class WordSearch {
    char[][] board;
    String word;

    boolean[][] finished;

    int length, breadth;
    int[] coordsX = {-1, 0, 1, 0};
    int[] coordsY = {0, 1, 0, -1};

    boolean isValid(int x, int y) {
        return !(x < 0 || x >= length || y < 0 || y >= breadth);
    }

    boolean dfs(int x, int y, int index) {
        finished[x][y] = true;
        ++index;
        //reached end of word
        if (index == word.length())
            return true;

        boolean res = false;

        for (int i = 0; i < 4; ++i) {
            int neighX = x + coordsX[i];
            int neighY = y + coordsY[i];
            //is within matrix, is undiscovered and its value is equal to next char
            if (isValid(neighX, neighY) && !finished[neighX][neighY] && board[neighX][neighY] == word.charAt(index))
                res |= dfs(neighX, neighY, index);
            else
                res |= false;
        }

        //if all neighbours not suitable, this means we took a wrong turn
        //hence we need to remark it to be unexplored so that it can be later explored via some other path
        if (!res) //just discovered 'unrolling' that you implement in DFS by myself!
            finished[x][y] = false;

        return res;

    }

    public boolean exist(char[][] board, String word) {
        if (word == null || word.equals(""))
            return true;
        if (board.length == 0)
            return false;

        this.board = board;
        this.word = word;
        length = board.length;
        breadth = board[0].length;

        boolean exists = false;

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < breadth; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    //found a source
                    finished = new boolean[length][breadth];
                    exists = dfs(i, j, 0);
                    if (exists)
                        break;
                }
            }
            if (exists)
                break;
        }
        return exists;
    }
}
