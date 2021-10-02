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
There are n cities. Some of them are connected, while some are not.
If city a is connected directly with city b, and city b is connected directly with city c,
then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and
the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

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

//Option 2
public class NumberofProvinces {

    Set<Integer> visited = new HashSet<>();
    int[][] isConnected;

    void doDFS(Integer i) {
        visited.add(i);
        for (int j = 0; j < isConnected[i].length; ++j) {
            if (j != i && isConnected[i][j] == 1 && !visited.contains(j)) {
                doDFS(j);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {

        int count = 0;
        this.isConnected = isConnected;

        for (int i = 0; i < isConnected.length; ++i) {
            if (!visited.contains(i)) {
                doDFS(i);
                count++;
            }
        }
        return count;

    }
}
