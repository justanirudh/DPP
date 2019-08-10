package com.anirudh.datastructures.graphs;

/*
332. Reconstruct Itinerary

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/

import java.util.*;

//Destructive DFS, like WordLadder
//can do normal BFS too, I guess
class ReconstructItinerary {

    Map<String, PriorityQueue<String>> flights; //new: priority queue
    LinkedList<String> path;

    //topological sort. the one that gets finished first is prepended first
    //topological sort is nothing but outputting the reverse order of DFS
    public void doDFS(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        //or can just mark it as discovered
        while (arrivals != null && !arrivals.isEmpty()) {
            String nextStop = arrivals.remove();
            doDFS(nextStop);
        }
        path.addFirst(departure);//prepending
    }

    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        //create graph
        for (String[] ticket : tickets) {
            //create a priority queue which always has lexicographically lowest elem as the 1st elem
            flights.putIfAbsent(ticket[0], new PriorityQueue<>()); //new: map has putIfAbsent
            flights.get(ticket[0]).add(ticket[1]);
        }
        //do DFS
        doDFS("JFK");
        return path;
    }


}