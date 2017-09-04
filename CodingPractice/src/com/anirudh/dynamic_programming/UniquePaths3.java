package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 9/3/17.
 */
/*
174. Dungeon Game

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists
of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his
way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0
or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other
rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal
path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */
public class UniquePaths3 {
    public int calculateMinimumHP(int[][] dungeon) {
        //go backwards
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] health = new int[m][n];

        health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1); //bottom right elem

        for (int i = m - 2; i >= 0; i--) {
            //if current cell negative, min health required increases (1- something negative)
            //if current sell positive, then min health requried decreases but shouldnt go below 1
            health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1); //rightmost column
        }

        for (int j = n - 2; j >= 0; j--) {
            health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1); //bottommost row
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
                health[i][j] = Math.min(right, down); //min of both as we require minimum health required
            }
        }

        return health[0][0];

    }
}
