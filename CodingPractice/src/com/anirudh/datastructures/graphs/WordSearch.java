package com.anirudh.datastructures.graphs;

/**
 * Created by paanir on 6/21/17.
 */
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

/*
    DFS in matrix (as multiple sources possible)
    like finding cycle in a directed graph. maintain a record of path
 */
//DFS with backtracking
public class WordSearch {
    char[][] board;
    String word;

    boolean[][] visited;

    int length, breadth;
    int[] coordsX = {-1, 0, 1, 0};
    int[] coordsY = {0, 1, 0, -1};

    boolean isValid(int x, int y) {
        return !(x < 0 || x >= length || y < 0 || y >= breadth);
    }

    boolean dfs(int x, int y, int index) { //index is index of word string
        visited[x][y] = true; //S, E
        ++index;
        //reached end of word
        if (index == word.length())
            return true;

        boolean res = false;
//curr is E, next find C
        for (int i = 0; i < 4; ++i) {
            int neighX = x + coordsX[i];
            int neighY = y + coordsY[i];
            //is within matrix, is undiscovered and its value is equal to next char
            if (isValid(neighX, neighY) && board[neighX][neighY] == word.charAt(index) && !visited[neighX][neighY]) //found E on top
                res = dfs(neighX, neighY, index);
            //else res will remain false
            if (res) //if found 1 true path, break. no need to check further
                return true;
        }

        //if all neighbours not suitable, this means we took a wrong turn
        //hence we need to re-mark it to be unexplored so that it can be later explored via some other path
        //just discovered 'unrolling' or recursion stack that we use for backtracking in DFS by myself!
        visited[x][y] = false;

        return false;

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

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < breadth; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    //found a source
                    visited = new boolean[length][breadth]; //new DFS for each source found
                    boolean exists = dfs(i, j, 0);
                    if (exists)
                        return true;
                }
            }
        }
        return false;
    }
}
