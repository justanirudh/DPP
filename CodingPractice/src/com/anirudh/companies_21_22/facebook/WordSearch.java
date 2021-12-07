package com.anirudh.companies_21_22.facebook;

/**
 * Created by paanir on 6/21/17.
 */
/*
79. Word Search

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

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
    DFS in matrix (as multiple sources possible) with backtracking
    like finding cycle in a directed graph. maintain a record of path
 */

public class WordSearch {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    char[][] board;
    boolean[][] visited;
    String word;

    boolean doDFS(int x, int y, int wordIdx) {
        visited[x][y] = true;
        if (wordIdx == word.length())
            return true;
        for (int z = 0; z < 4; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
                char letter = board[nx][ny];
                if (letter == word.charAt(wordIdx)) {
                    boolean wordExists = doDFS(nx, ny, wordIdx + 1);
                    if (wordExists)
                        return true;
                }
            }
        }
        visited[x][y] = false; //so that it can be used elsewhere
        return false;
    }


    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (word.charAt(0) == board[i][j]) {
                    visited = new boolean[board.length][board[0].length]; //new visited board for every run
                    boolean wordExists = doDFS(i, j, 1);
                    if (wordExists)
                        return true;
                }
            }
        }
        return false;
    }

}
