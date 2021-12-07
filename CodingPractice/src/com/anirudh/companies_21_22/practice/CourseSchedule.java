package com.anirudh.companies_21_22.practice;

import java.util.*;

//207. Course Schedule
public class CourseSchedule {
    /*
    finding a cycle in a DAG
    do DFS
     */

    Set<Integer> visited;
    Map<Integer, List<Integer>> graph;

    boolean doDFS(int course, Deque<Integer> path) {
        visited.add(course);
        path.push(course);
        for(int neighbour : graph.get(course)) {
            if(!visited.contains(neighbour)) {
                boolean hasCycle = doDFS(neighbour, path);
                if(hasCycle)
                    return true;
            }
            else { //it was visited in a previous run
                if(path.contains(neighbour))
                    return true;
            }
        }
        path.pop();
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //create graph
        graph = new HashMap<>(); // a -> b,c means a needs to be taken before b and c
        for(int i = 0; i < numCourses; ++i) {
            graph.put(i, new ArrayList<>());
            for(int[] prereq : prerequisites) {
                int first = prereq[1];
                int next = prereq[0];
                if(i == first)
                    graph.get(first).add(next);
            }
        }

        visited = new HashSet<>();
        //do dfs and find cycle
        for(int i = 0; i < numCourses; ++i) {
            if(!visited.contains(i)) {
                boolean hasCycle = doDFS(i, new ArrayDeque<>());
                if(hasCycle)
                    return false;
            }
        }
        return true;
    }
}
