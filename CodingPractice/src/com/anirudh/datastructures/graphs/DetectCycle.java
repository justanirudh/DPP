package com.anirudh.datastructures.graphs;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by paanir on 3/16/17.
 */
/*
G4G: http://www.practice.geeksforgeeks.org/problem-page.php?pid=700219

Detect cycle in an undirected graph
dfs  graph  union-find      Adobe    Amazon    Flipkart    Oracle
Given a undirected graph  your task is to complete the method isCycle  to detect if there is a cycle in the undirected graph or not. You should not read any input from stdin/console.
There are multiple test cases. For each test case, this method will be called individually.


Input (only to be used for Expected Output):
The first line of the input contains an integer 'T' denoting the number of test cases. Then 'T' test cases follow. Each test case consists of two lines.
Description of  test cases is as follows:
The First line of each test case contains two integers 'N' and 'M'  which denotes the no of vertices and no of edges respectively.
The Second line of each test case contains 'M'  space separated pairs u and v denoting that there is a bidirectional  edge from u to v .

Output:
The method should return true if there is a cycle else it should return false.

Constraints:
1 <=T<= 100
1<=N,M<=100
0 <=u,v<= N-1

Example:
Input:
2
2 2
0 1 0 0
4 3
0 1 1 2 2 3

Output:
1
0


In above first test case there is a graph with 2 vertices and 2 edges the first edge is from 0 to 1 and other edge is from 0 to 0 .
In the second test case there is a graph with 3 vertices and 3 edges from 0 to 1 , 1 to 2 and 2 to 3

**For More Examples Use Expected Output**
 */
public class DetectCycle {
    static Boolean doDFS(int curr, int parent, LinkedList<Integer>[] alist, int[] visited) {
        visited[curr] = 1; //visited this
        for (Integer child : alist[curr]) { //all neighbours
            if (visited[child] == 0) {
                boolean cycle = doDFS(child, curr, alist, visited); //IMP: dont return if false; only return if true
                if (cycle)
                    return true; //if false, check for next child
            } else { //visited
                if (child != parent) //make sure we are not looking at curr's parent
                    return true;
            }
        }
        return false;
    }

    static Boolean isCyclic(int V, LinkedList<Integer>[] alist) { //represented graph as an array of linked lists: index (node) to list of neighbours
        int[] visited = new int[V]; //no field of color gray or black as just an array. hence, using visited array to tally
        for (int i = 0; i < V; ++i)
            visited[i] = 0; //all not visited to start with
        for (int i = 0; i < V; ++i) {
            if (visited[i] == 0)
                if (doDFS(i, -1, alist, visited))
                    return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int v = in.nextInt();
            System.out.println("vertices: " + v);
            int e = in.nextInt();
            System.out.println("edges: " + e);
            LinkedList<Integer>[] alist = new LinkedList[v];
            for (int i = 0; i < v; i++) alist[i] = new LinkedList<Integer>();
            while (e-- > 0) {
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                alist[n1].add(n2);
                alist[n2].add(n1);
            }
            if (isCyclic(v, alist)) {
                System.out.println(1);
            } else
                System.out.println(0);
        }
    }
}
