package com.anirudh.interview_prep_2021.two_sigma;

import java.util.*;

/**
 * Created by paanir on 9/5/21.
 */
/*
547. Number of Provinces
Medium

3699

203

Add to List

Share
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.



Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 */

/*
Option 1: Create adjacency list from adjacency matrix. Then do DFS in adjacency matrix
Option 2: Do DFS directly in the adjacency matrix
 */
public class NumberofProvinces {

    Map<Integer, Set<Integer>> graph;
    Set<Integer> visited = new HashSet<>();

    void doDFS(Integer key) {
        visited.add(key);
        for (Integer neighbour : graph.get(key)) {
            if (!visited.contains(neighbour)) {
                doDFS(neighbour);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {

        graph = new HashMap<>();

        //create adjacency list from adj. matrix
        for (int i = 0; i < isConnected.length; ++i) {
            if (!graph.containsKey(i)) //add it here so that nodes with no neighbours count as provinces
                graph.put(i, new HashSet<>());
            for (int j = 0; j < isConnected[0].length; ++j) {
                if (i != j && isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    if (!graph.containsKey(j))
                        graph.put(j, new HashSet<>());
                    graph.get(j).add(i);
                }
            }
        }

        int count = 0;
        for (Integer key : graph.keySet()) {
            if (!visited.contains(key)) {
                doDFS(key);
                count++;
            }
        }
        return count;

    }
}
