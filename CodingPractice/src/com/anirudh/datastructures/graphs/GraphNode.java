package com.anirudh.datastructures.graphs;

import java.util.ArrayList;

/**
 * Created by paanir on 7/15/17.
 */
public class GraphNode {
    private char c; //using in Surrounded Regions problem
    private Coord coord;
    private String color;
    private ArrayList<Coord> neighbours;

    GraphNode(Coord coord) {
        this.coord = coord;
        color = "white";
        neighbours = new ArrayList<>();
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void addNeighbour(Coord coord) {
        neighbours.add(coord);
    }

    public ArrayList<Coord> getNeighbours() {
        return neighbours;
    }

    public void setC(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }
}
