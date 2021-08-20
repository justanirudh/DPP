package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 8/11/19.
 */
public class MostStonesRemovedwithSameRoworColumn {

    Set<Integer> visited; //will have index of coordinates in the graph array
    List<Integer>[] graph;
    int[][] stones;

    private void populateGraph() {
        for (int i = 0; i < stones.length; ++i) { //going through each coordinate
            for (int j = i + 1; j < stones.length; ++j) { //comparing the coordinate with rest of the array
                int[] coordi = stones[i];
                int[] coordj = stones[j];
                if (coordi[0] == coordj[0] || coordi[1] == coordj[1]) { //if both x same or both y same
                    if (graph[i] == null)
                        graph[i] = new ArrayList<>();
                    graph[i].add(j);
                    if (graph[j] == null)
                        graph[j] = new ArrayList<>();
                    graph[j].add(i);
                }
            }
        }
    }

    private void doDFS(int graphIndex) {
        visited.add(graphIndex);
        List<Integer> neighs = graph[graphIndex];
        for (int neigh : neighs) {
            if (!visited.contains(neigh)) {
                doDFS(neigh);
            }
        }
    }

    /*
    #moves = #stones - #islands
     */
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0)
            return 0;
        //create a graph
        visited = new HashSet<>();
        graph = (List<Integer>[]) new List[stones.length]; //array of lists
        this.stones = stones;
        populateGraph();
        //do DFS
        int numIslands = 0;
        for (int i = 0; i < graph.length; ++i) {
            if(graph[i] == null){ //this means no neighbours for this guy, so an island
                numIslands++;
            } else if (!visited.contains(i)) {
                doDFS(i);
                numIslands++;
            }
        }
        //return total reductions = number of stones - number of islands
        return stones.length - numIslands;
    }

}
