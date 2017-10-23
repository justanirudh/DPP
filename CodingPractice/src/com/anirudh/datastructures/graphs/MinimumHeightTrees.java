package com.anirudh.datastructures.graphs;

import com.anirudh.general_algos.IntegerToEnglishWords;

import java.util.*;

/**
 * Created by paanir on 10/19/17.
 */
/*
310. Minimum Height Trees
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted
tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
//LC 310 under construction
public class MinimumHeightTrees {

    //LOOK MA NO COLOR
    private class GraphNode {
        int val;
        List<Integer> neighbours;
        int dist;

        public GraphNode(int val) {
            this.val = val;
            neighbours = new ArrayList<>();
            dist = Integer.MAX_VALUE;
        }

        public void addNeighbours(int i) {
            neighbours.add(i);
        }
    }

    private Map<Integer, GraphNode> createGraph(int n, int[][] edges) {
        Map<Integer, GraphNode> graph = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            graph.put(i, new GraphNode(i));
        }
        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            graph.get(v1).addNeighbours(v2);
            graph.get(v2).addNeighbours(v1);
        }
        return graph;
    }

    //find max distance
    private int doBFS(int root, Map<Integer, GraphNode> graph, String[] colors) {
        GraphNode node = graph.get(root);
        colors[root] = "gray";
        node.dist = 0;
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        int maxDist = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            GraphNode gn = queue.remove();
            int currDist = gn.dist;
            if (currDist > maxDist)
                maxDist = currDist;
            for (int neigh : gn.neighbours) {
                GraphNode neighNode = graph.get(neigh);
                if (colors[neighNode.val].equals("white")) {
                    neighNode.dist = currDist + 1;
                    colors[neighNode.val] = "gray";
                    queue.add(neighNode);
                }
            }
            colors[gn.val] = "black";
        }
        return maxDist;
    }


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        //do bfs in all vertices and find the ones with min height
        Map<Integer, GraphNode> graph = createGraph(n, edges);

        int min = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        String[] colors = new String[n];
        for (int i = 0; i < n; ++i) {
            colors[i] = "white";
        }

        //Sol.1 : put creation of graph inside loop
        //Sol.2: separate array for colors
        for (int root = 0; root < n; ++root) {
            //create graph
            //this needs to be inside loop as new graph created everytime is required as
            //fresh BFS everytime. else deepcopy reqd which is not that straightforward

            int curr_min = doBFS(root, graph, colors.clone());
            if (curr_min < min) {
                min = curr_min;
                res = new ArrayList<>();
                res.add(root);
            } else if (curr_min == min)
                res.add(root);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] arr = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        MinimumHeightTrees mht = new MinimumHeightTrees();
        List<Integer> res = mht.findMinHeightTrees(n, arr);
        for (int i :
                res) {
            System.out.println(i);
        }
    }
}
