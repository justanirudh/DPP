package com.anirudh.datastructures.graphs;
//DFS

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 1/28/17.
 */
public class TwoSigmaFriendCircles {

    //white = undiscovered, grey = discovered, black = finished
    private static class Node {
        Integer num;
        String color = "white";
        ArrayList<Integer> neighbors = new ArrayList<>();

        public Node(Integer num) {
            this.num = num;
        }
    }

    static void doDFS(Map<Integer, Node> graph, Integer curr) {
        graph.get(curr).color = "grey";

        for(Integer i: graph.get(curr).neighbors){
            if(graph.get(i).color.equals("white"))
               doDFS(graph, i);
        }

        graph.get(curr).color = "black";
    }


    static int friendCircles(String[] friends) {

        //create graph
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < friends.length; ++i) {
            String curr = friends[i];
            Node node = new Node(i);
            for (int j = 0; j < curr.length(); ++j) {
                if (i != j && curr.charAt(j) == 'Y') {
                    node.neighbors.add(j);
                }
            }
            graph.put(i, node);
        }
        //do dfs
        int forests = 0;

        for(Integer i : graph.keySet()){
            if(graph.get(i).color.equals("white")){
                forests++;
                doDFS(graph, i);
            }
        }

        return forests;
    }

    public static void main(String[] args) {

        String[] s = new String[]{"YYNN", "YYYN", "NYYN", "NNNY"}; // adjacency matrix
        System.out.println(friendCircles(s));
    }
}
