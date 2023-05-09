package com.anirudh.a_prep_2023.bloomberg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
797. All Paths From Source to Target
Medium
6.5K
135
company
Bloomberg
company
Adobe
company
Google
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).



Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]


Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 */
public class AllPathsFromSourcetoTarget {
    //No visited required as it is a DAG
    Deque<Integer> path;
    List<List<Integer>> res = new ArrayList<>();
    int[][] graph;

    void doDFS(int src) {
        if (src == graph.length - 1) { //found
            List<Integer> list = new ArrayList<>(path); // convert queue to list
            list.add(src); // add last elem
            res.add(list); //add the list to res
            return;
        }
        path.addLast(src);
        for (int neigh : graph[src]) {
            doDFS(neigh);
        }
        path.removeLast();
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        for (int neigh : graph[0]) { //new visited set for each path
            path = new ArrayDeque<>();
            path.add(0);
            doDFS(neigh);
        }
        return res;
    }
}
