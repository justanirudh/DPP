package com.anirudh.companies_21_22.google;

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

/*
Created directed graph DAG
Then do DFS.
DONT use visited set as we are coming back to visited airports because it is a DAG
Instead just keep polling from value lists until it is null. Destructive but works
 */

import java.util.*;

class ReconstructItinerary {

    private Map<String, PriorityQueue<String>> flights;
    private Deque<String> path;

    private void doDFS(String departure) {
        Queue<String> arrivals = flights.get(departure);

        while (arrivals != null && !arrivals.isEmpty()) {
            String nextStop = arrivals.poll();
            doDFS(nextStop);
        }
        path.push(departure); //topological sort. the one that gets finished first is prepended first
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new ArrayDeque<>();

        //create graph
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>()); //create a priority queue which always has lexicographically lowest elem as the 1st elem
            flights.get(ticket.get(0)).offer(ticket.get(1));
        }

        //do DFS
        doDFS("JFK");

        return new ArrayList<>(path);
    }


}