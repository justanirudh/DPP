package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
827. Making A Large Island
Hard

1981

46

Add to List

Share
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
Accepted
90,644
Submissions
203,360
 */
/*
Approach 1:
1. do DFS in each '1' cell WHILE mutating the grid with key for Map {key -> size of the island}
    O(mn) as each cell will be only traversed once
    start key with 2 so as not to mess up with 0 and 1
2. Now flip each 0 to 1
    find its neighbours
    make a set of their keys (so that cells which belong to same graph are not counted twice)
    get total area from map + 1
    get max
    Again: O(mn)

Approach 2: Flip each 0 and find size by doing DFS; Find max size among them
Tx: mn * mn
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[][] grid;
    int currSize;

    boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    void dfs(int i, int j, int key) {
        grid[i][j] = key;
        currSize++;
        for (int k = 0; k < 4; ++k) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (isValid(x, y) && grid[x][y] == 1) {
                dfs(x, y, key);
            }
        }
    }


    public int largestIsland(int[][] grid) {
        this.grid = grid;
        Map<Integer, Integer> idToSize = new HashMap<>();
        int key = 2;
        boolean noZeroes = true;
        boolean noOnes = true;
        for (int i = 0; i < grid.length; ++i) { //1. mutate graph to get ids -> size
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    currSize = 0;
                    dfs(i, j, key);
                    idToSize.put(key, currSize);
                    key++;
                    noOnes = false;
                }
                if (grid[i][j] == 0)
                    noZeroes = false;
            }
        }
        if (noZeroes) { //for all 1s case
            return grid.length * grid[0].length;
        }

        if (noOnes) { // for all 0s case
            return 1;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; ++i) { //2. for every 0, find size
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    Set<Integer> ids = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (isValid(x, y)) {
                            ids.add(grid[x][y]);
                        }
                    }
                    int sum = 0;
                    for (int id : ids) {
                        if (id != 0) //if neighbour is 0 (if it was 1, it was changed to a key)
                            sum += idToSize.get(id);
                    }
                    max = Math.max(max, sum + 1);
                }

            }
        }
        return max;
    }
}


