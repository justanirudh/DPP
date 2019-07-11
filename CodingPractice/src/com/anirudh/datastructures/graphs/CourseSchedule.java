package com.anirudh.datastructures.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by paanir on 9/29/17.
 */
/*
207. Course Schedule
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course
0 you should also have finished course 1. So it is impossible.
 */

//equivalent to finding a CYCLE IN A DIRECTED GRAPH: DFS
public class CourseSchedule {

    boolean[] visited;
    Deque<Integer> path;

    // course number (cn) -> all courses that can be taken if cn is taken
    private List<List<Integer>> createGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int src = 0; src < numCourses; ++src) {
            graph.add(src, new ArrayList<>());
            for (int[] prereq : prerequisites) {
                if (prereq[1] == src) { //got an edge
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
        for (int dist : dists) {
            if (!visited[dist]) {
                //check cycleExists
                boolean cycleExists = hasCycle(dist, graph);
                if (cycleExists)
                    return true;
            } else {
                //if already visited, check if visited in this traversal
                if (path.contains(dist))
                    return true; //if visited in this traversal, we have a cycle
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
        for (int i = 0; i < numCourses; ++i) {//go over all trees
            boolean cycleExists = hasCycle(i, graph);
            if (cycleExists)
                return false;
        }
        return true;
    }
}
