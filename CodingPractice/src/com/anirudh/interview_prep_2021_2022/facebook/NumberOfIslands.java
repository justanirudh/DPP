package com.anirudh.interview_prep_2021_2022.facebook;

/**
 * Created by paanir on 3/25/17.
 */
//DFS
/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */
public class NumberOfIslands {

    //Do DFS in matrix

    private boolean[][] visited;
    private int[] di = {0,0,1,-1};
    private int[] dj = {1,-1,0,0};
    private int numRows;
    private int numCols;
    private char[][] grid;

    private boolean isValid(int row, int col){
        if(row < 0 || col < 0 || row >= numRows || col >= numCols )
            return false;
        return true;
    }

    private void doDFS(int row, int col) {
        visited[row][col] = true;
        for(int i = 0; i < 4; ++i){ //neighbours
            int x = row + di[i];
            int y = col + dj[i];
            if(isValid(x,y) && grid[x][y] == '1' && !visited[x][y]){
                doDFS(x,y);
            }
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        this.grid = grid;
        numRows = grid.length;
        numCols = grid[0].length;
        visited = new boolean[numRows][numCols]; //all false
        int numIslands = 0;

        for(int i = 0; i < numRows; ++i){
            for(int j = 0; j < numCols; ++j){
                if(grid[i][j] == '1' && !visited[i][j]){
                    doDFS(i, j);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }
}
