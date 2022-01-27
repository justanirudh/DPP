package com.anirudh.companies_21_22.google;
/**
 * 947. Most Stones Removed with Same Row or Column
 * Medium
 * <p>
 * 1833
 * <p>
 * 476
 * <p>
 * Add to List
 * <p>
 * Share
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * <p>
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * <p>
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * No two stones are at the same coordinate point.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MostStonesRemovedSameRowColumn {

    Set<List<Integer>> visited;
    int[][] stones;

    void doDFS(int[] source) {
        visited.add(Arrays.asList(source[0], source[1]));
        for (int[] stone : stones) {
            if ((stone[0] == source[0] || stone[1] == source[1])
                    && !visited.contains(Arrays.asList(stone[0], stone[1]))) {
                doDFS(stone);
            }
        }
    }

    public int removeStones(int[][] stones) {
        int numStones = stones.length;
        visited = new HashSet<>();
        this.stones = stones;

        int stonesToKeep = 0;
        for (int[] stone : stones) {
            if (!visited.contains(Arrays.asList(stone[0], stone[1]))) {
                doDFS(stone);
                stonesToKeep++;
            }
        }
        return numStones - stonesToKeep; // = stonesToRemove
    }
}
