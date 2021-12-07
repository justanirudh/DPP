package com.anirudh.companies_21_22.google;

import java.util.*;

/**
 * Created by paanir on 8/11/19.
 */

//Problem #947

/**
 * 947. Most Stones Removed with Same Row or Column
 * Medium
 * 
 * 1833
 * 
 * 476
 * 
 * Add to List
 * 
 * Share
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * 
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * 
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 * 
 * 
 * Example 1:
 * 
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
 * 
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 * 
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 * 
 * 
 * Constraints:
 * 
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * No two stones are at the same coordinate point.
 */

/*
Key here is to create graph NOT based on coordinates but based on the neighbour relationship
2 stones are neighbours IF they share same row or column. After creating graph, do normal DFS
num islands  = num of stones to keep
 */

public class MostStonesRemovedwithSameRoworColumn {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    void doDFS(Integer stone) {
        visited.add(stone);
        for (Integer neighbour : graph.get(stone)) {
            if (!visited.contains(neighbour))
                doDFS(neighbour);
        }
    }

    //find largest number of stones that can be removed
    public int removeStones(int[][] stones) {
        //create graph
        for (int i = 0; i < stones.length; ++i) {
            int[] src = stones[i];
            if (!graph.containsKey(i))
                graph.put(i, new ArrayList<>());
            for (int j = i + 1; j < stones.length; ++j) {
                int[] curr = stones[j];
                if (src[0] == curr[0] || src[1] == curr[1]) {
                    graph.get(i).add(j);
                    if (!graph.containsKey(j))
                        graph.put(j, new ArrayList<>());
                    graph.get(j).add(i);
                }
            }
        }
        //do DFS to find islands
        int keepStones = 0;
        for (Integer stoneIdx : graph.keySet()) {
            if (!visited.contains(stoneIdx)) {
                doDFS(stoneIdx);
                keepStones++;
            }
        }
        return stones.length - keepStones;
    }

}
