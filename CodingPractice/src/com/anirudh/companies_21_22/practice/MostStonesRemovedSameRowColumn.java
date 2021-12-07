package com.anirudh.companies_21_22.practice;

import java.util.HashSet;
import java.util.Set;

public class MostStonesRemovedSameRowColumn {

    Set<int[]> visited;
    int[][] stones;

    void doDFS(int[] source) {
        visited.add(source);
        for(int[] stone : stones) {
            if(!visited.contains(stone) && (stone[0] == source[0] || stone[1] == source[1])) {
                doDFS(stone);
            }
        }
    }

    public int removeStones(int[][] stones) {
        int numStones = stones.length;
        visited = new HashSet<>();
        this.stones = stones;

        int connectedComponents = 0;
        for(int[] stone : stones) {
            if(!visited.contains(stone)) {
                doDFS(stone);
                connectedComponents++;
            }
        }
        return numStones - connectedComponents;
    }
}
