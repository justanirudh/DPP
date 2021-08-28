package com.anirudh.datastructures.graphs;

import java.util.*;

/**
 * Created by paanir on 7/14/19.
 */
/*
399. Evaluate Division
Medium

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].



The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 */
public class EvaluateDivision {
    /*
    - No GraphNode required: Graph is from string to list of <edge with weights>
    - needed an extra visited map as we are using teh graph multiple times
    -- use a visited set. add to set if visited
    - needed an Edge class for per src-dest weights
    - needed a Path class to save state of product of each path traversed until now
     */
    private class Edge {
        double weight;
        String dest;

        Edge(double weight, String dest) {
            this.weight = weight;
            this.dest = dest;
        }
        //getters and setters
    }

    private class Path {
        double product;
        String lastNode;

        Path(double product, String lastNode) {
            this.product = product;
            this.lastNode = lastNode;
        }
        //getters and setters    
    }

    private void populateGraph(Map<String, Set<Edge>> graph, List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); ++i) {
            List<String> equation = equations.get(i); // [a,b]
            double value = values[i];// 2
            String node1 = equation.get(0);
            String node2 = equation.get(1);
            graph.put(node1, new HashSet<>());
            graph.put(node2, new HashSet<>());
            graph.get(node1).add(new Edge(value, node2));
            graph.get(node2).add(new Edge(1 / value, node1));
        }
    }

    private double doBFS(Map<String, Set<Edge>> graph, List<String> query, Set<String> visited) {
        String source = query.get(0);
        String dest = query.get(1);
        if (!graph.containsKey(source) || !graph.containsKey(dest))
            return -1;
        if (source.equals(dest)) //[a,a]
            return 1;

        Deque<Path> paths = new ArrayDeque<>();
        paths.offer(new Path(1.0, source));

        while (!paths.isEmpty()) {
            Path path = paths.poll();
            visited.add(path.lastNode);
            Set<Edge> edges = graph.get(path.lastNode);
            for (Edge edge : edges) {
                String neighbour = edge.dest;
                if (!visited.contains(neighbour)) { //not visited
                    double pathWeight = path.product * edge.weight;
                    if (neighbour.equals(dest)) {
                        return pathWeight; //found answer
                    } else {
                        paths.offer(new Path(pathWeight, neighbour));
                    }
                }
            }
        }

        return -1;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || equations.size() == 0 || values == null || values.length == 0 ||
                equations.size() != values.length || queries == null || queries.size() == 0)
            return new double[0];
        //1. create graph: adjacency list
        Map<String, Set<Edge>> graph = new HashMap<>();
        populateGraph(graph, equations, values);
        //2. traverse it and answer queries
        //do BFS for each query
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            res[i] = doBFS(graph, queries.get(i), new HashSet<>());
        }
        return res;
    }
}
