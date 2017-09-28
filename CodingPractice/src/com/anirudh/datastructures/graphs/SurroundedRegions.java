package com.anirudh.datastructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//BFS, can be done by DFS too
/**
 * Created by paanir on 3/27/17.
 */
/*
130. Surrounded Regions
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
 */
public class SurroundedRegions {
    /*
    In this problem, only the cells on the borders can not be surrounded.
    So we replace O's with '#' if they are on borders, and then scan the board and replace all O's left (if any).
     */
    /*
    http://www.programcreek.com/2014/04/leetcode-surrounded-regions-java/
    Rather do BFS in the matrix, no need to make an adjacency list
     */
    private static int leftLim;
    private static int rightLim;
    private static int upLim;
    private static int downLim;


    public static GraphNode createNode(char[][] board, Coord coord) {
        GraphNode gn = new GraphNode(coord);
        int x = coord.getX();
        int y = coord.getY();

        if (x - 1 >= upLim && board[x - 1][y] == 'O')
            gn.addNeighbour(new Coord(x - 1, y));
        if (x + 1 <= downLim && board[x + 1][y] == 'O')
            gn.addNeighbour(new Coord(x + 1, y));
        if (y - 1 >= leftLim && board[x][y - 1] == 'O')
            gn.addNeighbour(new Coord(x, y - 1));
        if (y + 1 <= rightLim && board[x][y + 1] == 'O')
            gn.addNeighbour(new Coord(x, y + 1));

        gn.setC('O');

        return gn;
    }

    public static HashMap<Coord, GraphNode> createGraph(char[][] board) {
        HashMap<Coord, GraphNode> graph = new HashMap<>();
        for (int x = 0; x < board.length; ++x) {
            for (int y = 0; y < board[0].length; ++y) {
                if (board[x][y] == 'O') { //if can be captured
                    Coord coord = new Coord(x, y);
                    GraphNode gn = createNode(board, coord);
                    graph.put(coord, gn);
                }
            }
        }
        return graph;
    }

    public static void doBFS(HashMap<Coord, GraphNode> graph, Coord coord) { //can also do dfs, doesnt matter
        GraphNode node = graph.get(coord);
//        node.setColor("gray");
        node.setC('#');
        Queue<Coord> queue = new LinkedList<>();
        queue.add(coord);
        while (!queue.isEmpty()) {
            Coord c = queue.remove();
            for (Coord child : graph.get(c).getNeighbours()) {
                if (graph.get(child).getC() != '#') { //normal BFS: check if white
                    graph.get(child).setC('#');
                    queue.add(child);
                }
            }
//            graph.get(c).setColor("black");
        }
    }

    public static void solve(char[][] board) {
        if (board.length == 0)
            return;

        leftLim = 0;
        rightLim = board[0].length - 1;
        upLim = 0;
        downLim = board.length - 1;

        //create graph
        HashMap<Coord, GraphNode> graph = createGraph(board);

        //print graph
//        for (Coord c : graph.keySet()) {
//            System.out.print("(" + c.getX() + "," + c.getY() + "," + graph.get(c).getC() + ") -> ");
//            for (Coord n : graph.get(c).getNeighbours()) {
//                System.out.print("(" + n.getX() + "," + n.getY() + "), ");
//            }
//            System.out.println();
//        }

        //do bfs on each node on [the edge of the matrix && which is a O] and convert the values of all the traversed graph to '#'
        for (Coord coord : graph.keySet()) {
            int x = coord.getX();
            int y = coord.getY();
            if (x == upLim || x == downLim || y == leftLim || y == rightLim)
                doBFS(graph, coord);
        }

        //print graph again
//        for (Coord c : graph.keySet()) {
//            System.out.print("(" + c.getX() + "," + c.getY() + "," + graph.get(c).getC() + ") -> ");
//            for (Coord n : graph.get(c).getNeighbours()) {
//                System.out.print("(" + n.getX() + "," + n.getY() + "), ");
//            }
//            System.out.println();
//        }

        //set new entries to table
        for (Coord coord : graph.keySet()) {
            char c = graph.get(coord).getC(); //either O or #
            if (c != '#') //if not a boundary 0
                board[coord.getX()][coord.getY()] = 'X'; //change it to captured
        }

        //print board
        for (int x = 0; x < board.length; ++x) {
            for (int y = 0; y < board[0].length; ++y) {
                System.out.print(board[x][y] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] g = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(g);
    }
}
