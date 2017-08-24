package com.anirudh.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

//LC332 under-construction
class ReconstructItinerary {

    class Node {
        String from;
        List<String> tos;
        Node(String from){
            this.from = from;
            tos = new ArrayList<>();
        }
    }

    public List<String> doDFS(Map<String, Node> graph, List<String> itin) {
        String from = itin.get(itin.size() - 1);
        Node node = graph.get(from); //get all neighbours of last elem of the itin (curr elem)
        List<String> tos = node.tos; //get all neighbours of last elem of the itin (curr elem)
        for(String to : tos){
            itin.add(to); //add to itin
            graph.get(from).tos.remove(0); //remove first element (that just got added to itin)
            doDFS(graph, itin);
        }
        return itin;
    }


    public List<String> findItinerary(String[][] tickets) {
        Map<String, Node> graph = new HashMap<>();
        //graph creation
        for(String[] path : tickets){
            String from = path[0];
            String to = path[1];
            if(!graph.containsKey(from))
                graph.put(from, new Node(from));
            if(!graph.containsKey(to))
                graph.put(to, new Node(to)); //to not have NPE when reach last destination
            List<String> tos = graph.get(from).tos;
            tos.add(to);
            Collections.sort(tos); //no need to sort everytime. Just put elem in right place lexicographically
            // graph.put(from, tos);
        }
        //DFS
        List<String> itin = new ArrayList<>();
        itin.add("JFK");
        return doDFS(graph, itin );
    }
}