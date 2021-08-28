package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 8/11/19.
 */

//Problem #947
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
        //do DFS to find keepStones
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
