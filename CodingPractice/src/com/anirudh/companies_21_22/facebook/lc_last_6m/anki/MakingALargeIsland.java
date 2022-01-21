package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

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
Approach:
1. do DFS in each '1' cell WHILE mutating the grid with key for Map {key -> size of the island}
    O(mn) as each cell will be only traversed once
2. Now flip each 0 to 1
    find its neighbours
    make a set of their keys (so that cells which belong to same graph are not counted twice)
    add it to total area + 1
    Again: O(mn)

Approach: Flip each 0 and find size by doing DFS; Find max size among them
Tx: mn * mn
 */

public class MakingALargeIsland {
}
