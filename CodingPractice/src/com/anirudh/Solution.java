package com.anirudh;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import java.util.List;

/**
 * Created by paanir on 6/15/19.
 */
class Solution {

    boolean[] visited;
    Deque<Integer> path;

    // course number (cn) -> all courses that can be taken if cn is taken
    private List<List<Integer>> createGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int src = 0; src < numCourses; ++src){
            for(int[] prereq : prerequisites){
                if(prereq[1] == src) { //got an edge
                    if(graph.get(src) == null)
                        graph.add(src, new ArrayList<>());
                    graph.get(src).add(prereq[0]);
                }
            }
        }
        return graph;
    }

    private boolean hasCycle(int src, List<List<Integer>> graph) {
        visited[src] = true;
        path.push(src);
        List<Integer> dists = graph.get(src);
        for(int dist : dists){
            if(!visited[dist]){
                //check cycleExists
                boolean cycleExists = hasCycle(dist, graph);
                if(cycleExists)
                    return true;
            }
            else{
                //if already visited, check if visited in this traversal
                if(path.contains(dist))
                    return true;
            }
        }
        //no cycles in this traversal
        path.pop();
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = createGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        path = new ArrayDeque<>();
        for(int i = 0; i < numCourses; ++i){//go over all trees
            boolean cycleExists = hasCycle(i, graph);
            if(cycleExists)
                return false;
        }
        return true;
    }
}