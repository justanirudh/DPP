package com.anirudh.datastructures.graphs;

import java.util.LinkedList;
import java.util.Scanner;
import java.lang.*;

/**
 * Created by paanir on 3/21/17.
 */

//http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
//https://courses.csail.mit.edu/6.006/fall11/rec/rec14.pdf

//Directed graph: If it is visited && it is in the recursion 'stack' (if parent it will be, so no need to check alag se)
//recursion 'stack' is a hashmap from node to whether it is in the current path or not
class DetectCycleInDirectedGraph {

    //essentially this:
    //If it hasn't been visited, visit it
    //else, check if it is in the path covered till now (using a 'array', or more generically, a hashmap data structure called 'recursion stack' for keeping track)
    //ADVANTAGE OF ARRAY OVER CHANGING THE COLOR OF NODE: If the graph is used for multiple searches or is being searched concurrently,
    //one array oer search is better
    //SO, equivalent to undirect, except, also keep an extra hashmap to track of the current path
    public boolean hasCycle(int v, LinkedList<Integer>[] alist, boolean[] visited, boolean[] recStack) {
        if (!visited[v]) {
            recStack[v] = true; //adding it to the 'path' that lead to the current node
            visited[v] = true; // can be a property of the node
            for (int i : alist[v]) {
                if (!visited[i]) { //if hasn't been visited and has cycle
                    boolean cycle = hasCycle(i, alist, visited, recStack);
                    if (cycle)
                        return true;
                } else { //if visited, check if it is in the recursion 'stack'
                    if (recStack[i]) //if neighbour in recursion stack, then this is a back edge
                        return true;
                }
            }
            recStack[v] = false; //remove vertex from recursion stack
        }
        return false;
    }

    // //represented graph as an array of linked lists: index (node) to list of neighbours, same as undirected question
    //alist is an array of linked lists; former having nodes and latter having neighbours
    public boolean hasCycleUtil(int v, LinkedList<Integer>[] alist, boolean[] visited, boolean[] recStack) {
        for (int i = 0; i < v; i++) {
            if (hasCycle(i, alist, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int v = in.nextInt();
            int e = in.nextInt();
            LinkedList<Integer>[] alist = new LinkedList[v];
            for (int i = 0; i < v; i++)
                alist[i] = new LinkedList<Integer>();
            while (e-- > 0) {
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                alist[n1].add(n2);
            }
            boolean[] visited = new boolean[v];
            boolean[] recStack = new boolean[v];
            DetectCycleInDirectedGraph g = new DetectCycleInDirectedGraph();
            if (g.hasCycleUtil(v, alist, visited, recStack)) {
                System.out.println(1);
            } else
                System.out.println(0);
        }
    }
}