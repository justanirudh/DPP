package com.anirudh.interview_prep_2021_2022.two_sigma;

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

import java.util.*;

/*
Do it inplace
1 -> <2 1s -> 0
1 -> 2/3 1s -> 1
1 -> >3 1s -> 0
0 -> 3 1s -> 1

Since in-place:
if 0 -> 1 => 0 -> 2 (current state is 0, next state is 1)
if 1 -> 0 => 1 -> -2 (current state is 1, next state is 0)

READ scalability answer
1. huge board but can fit in memory: If Sparse matrix, only store indices of 1s

What I do is I have the coordinates of all living cells in a set.
Then I count the living neighbors of all cells by going through the living cells and increasing the counter of their
neighbors (thus cells without living neighbor will not be in the counter). Afterwards I just collect the new set of
living cells by picking those with the right value (0 or 1) and right amount of neighbors.

2. cannot fit in memory: If huge matrix, store in file with each row in each line. For finding the next state of all cells in a row,
we only need the adjacent top row and adjacent bottom row. So, at a time, only need to load 3 lines in memeory,
rest can be in disk


 */
public class GameOfLife_Large {

    int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};
    int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};

    private Set<List<Integer>> getNextLiveCells(Set<List<Integer>> live) {
        Map<List<Integer>, Integer> neighbours = new HashMap<>();
        for (List<Integer> cell : live) { //for all cells having at least one 1 as neighbour, count all 1s in their neighbourhood
            for (int k = 0; k < 8; ++k) {
                int x = cell.get(0) + dx[k];
                int y = cell.get(1) + dy[k];
                List<Integer> coords = Arrays.asList(x, y);
                neighbours.put(coords, neighbours.getOrDefault(coords, 0) + 1);
            }
        }

        //go through the neighbours and change them to their next states, WE ONLY CARE ABOUT the neighbours that are changing to 1 state
        Set<List<Integer>> newLive = new HashSet<>();
        for (Map.Entry<List<Integer>, Integer> cell : neighbours.entrySet()) {
            if (live.contains(cell.getKey()) && (cell.getValue() == 2 || cell.getValue() == 3)) //1 -> 1
                newLive.add(cell.getKey());
            else if (!live.contains(cell.getKey()) && cell.getValue() == 3) //0 -> 1
                newLive.add(cell.getKey());
        }
        return newLive;
    }


    public void gameOfLife(int[][] board) {
        Set<List<Integer>> live = new HashSet<>(); //set of all points
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    live.add(Arrays.asList(i, j));
                }
            }
        }
        live = getNextLiveCells(live);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = live.contains(Arrays.asList(i, j)) ? 1 : 0;
            }
        }
    }
}
