package com.anirudh.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by paanir on 3/25/17.
 */
//DFS
/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */
public class NumberOfIslands {

    private static int leftLim;
    private static int rightLim;
    private static int upLim;
    private static int downLim;

    public static GraphNode createNode(char[][] grid, Coord coord) {
        GraphNode gn = new GraphNode(coord);
        int x = coord.getX();
        int y = coord.getY();

        if (x - 1 >= upLim && grid[x - 1][y] == '1')
            gn.addNeighbour(new Coord(x - 1, y));
        if (x + 1 <= downLim && grid[x + 1][y] == '1')
            gn.addNeighbour(new Coord(x + 1, y));
        if (y - 1 >= leftLim && grid[x][y - 1] == '1')
            gn.addNeighbour(new Coord(x, y - 1));
        if (y + 1 <= rightLim && grid[x][y + 1] == '1')
            gn.addNeighbour(new Coord(x, y + 1));

        return gn;
    }

    public static HashMap<Coord, GraphNode> createGraph(char[][] grid) {
        HashMap<Coord, GraphNode> graph = new HashMap<>();
        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid[0].length; ++y) {
                if (grid[x][y] == '1') { //if island
                    Coord coord = new Coord(x, y);
                    GraphNode gn = createNode(grid, coord);
                    graph.put(coord, gn);
                }
            }
        }
        return graph;
    }

    public static void performDFS(HashMap<Coord, GraphNode> graph, Coord origin) {
        GraphNode gn = graph.get(origin);
        gn.setColor("gray");

        for (Coord n : gn.getNeighbours()) {
            if (graph.get(n).getColor().equals("white"))
                performDFS(graph, n);
        }
        gn.setColor("black");
    }

    public static int getIslands(HashMap<Coord, GraphNode> graph) {
        int num = 0; //number of connected components
        for (Coord c : graph.keySet()) {
            if (graph.get(c).getColor().equals("white")) {
                num++;
                performDFS(graph, c);
            }
        }
        return num;
    }

    public static int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        leftLim = 0;
        rightLim = grid[0].length - 1;
        upLim = 0;
        downLim = grid.length - 1;

        HashMap<Coord, GraphNode> graph = createGraph(grid);

        //get islands
        return getIslands(graph);
    }

    public static void main(String[] args) {
        char[][] g = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands(g));

    }
}
