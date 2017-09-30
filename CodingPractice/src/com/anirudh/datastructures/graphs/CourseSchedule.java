package com.anirudh.datastructures.graphs;

import java.util.ArrayList;
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

//equivalent to finding a CYCLE IN A DIRECTED GRAPH
public class CourseSchedule {

    boolean[] visited;
    boolean[] inRecStack;

    boolean doDFS(List<Integer>[] graph, int src) {
        visited[src] = true;
        inRecStack[src] = true;
        List<Integer> neighbours = graph[src];
        if (neighbours == null) { // for leaf courses like 0 -> 1, 1 has no 'to neighbours'
            inRecStack[src] = false; //unwind
            return true;
        }
        for (int n : neighbours) {
            if (!visited[n]) {
                boolean canFinish = doDFS(graph, n);
                if (!canFinish)
                    return false;
            } else {
                if (inRecStack[n])
                    return false;
            }
        }
        inRecStack[src] = false; //unwind
        return true;
    }

    List<Integer>[] createGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] lists = (ArrayList<Integer>[]) new ArrayList[numCourses];
        for (int[] edge : prerequisites) {
            int source = edge[1];
            int dest = edge[0];
            if (lists[source] == null) //initialize
                lists[source] = new ArrayList<>();
            lists[source].add(dest);
        }
        return lists;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        inRecStack = new boolean[numCourses];
        //create adjacency list a an array of arraylists
        List<Integer>[] graph = createGraph(numCourses, prerequisites);
        //DFS to find a cycle in graph
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i]) {
                boolean canFinish = doDFS(graph, i);
                if (!canFinish)
                    return false;
            }
        }
        return true;
    }
}
