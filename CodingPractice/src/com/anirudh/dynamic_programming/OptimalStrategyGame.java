package com.anirudh.dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paanir on 10/29/17.
 */
/*
Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even.
We play a game against an opponent by alternating turns. In each turn, a player selects either
the first or last coin from the row, removes it from the row permanently, and receives the value of
the coin. Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.
 */
public class OptimalStrategyGame {
    //http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
    //https://www.youtube.com/watch?v=WxpIHvsu1RI

    /*
    F(i, j)  represents the maximum value the user can collect from
         i'th coin to j'th coin.

    F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), //doing min as the opponent intends to choose the coin which leaves the user with minimum value.
                   Vj + min(F(i+1, j-1), F(i, j-2) ))
    Base Cases
    F(i, j)  = Vi           If j == i
    F(i, j)  = max(Vi, Vj)  If j == i+1
     */

    static Map<List<Integer>, Integer> map = new HashMap<>();

    static void addToMap(Map<List<Integer>, Integer> map, int start, int end, int val) {
        List<Integer> endpoints = new ArrayList<>();
        endpoints.add(start);
        endpoints.add(end);
        map.put(endpoints, val);
    }

    static Integer getFromMap(Map<List<Integer>, Integer> map, int start, int end) {
        List<Integer> endpoints = new ArrayList<>();
        endpoints.add(start);
        endpoints.add(end);
        return map.get(endpoints);
    }

    public static int optimalStrategyResult(int[] coins, int start, int end) {

        Integer optimal = getFromMap(map, start, end);
        if(optimal != null)
            return optimal;

        if (start == end)
            return coins[start];
        if (end == start + 1)
            return Math.max(coins[start], coins[end]);

        //I took from left
        int oppoLeft = optimalStrategyResult(coins, start + 2, end); //opponent then also took from left
        addToMap(map, start + 2, end, oppoLeft);
        int oppoRight = optimalStrategyResult(coins, start + 1, end - 1); //opponent them took from right
        addToMap(map, start + 1, end - 1, oppoRight);
        int leftEnd = coins[start] + Math.min(oppoLeft, oppoRight); //I took from left end


    //I took from right
        oppoLeft = optimalStrategyResult(coins, start + 1, end - 1); //opponent took from left
        addToMap(map, start + 1, end - 1, oppoLeft); // not required but for the sake of symmetry
        oppoRight = optimalStrategyResult(coins, start, end - 2); //opponent also took from right
        addToMap(map, start, end - 2, oppoRight);
        int rightEnd = coins[end] + Math.min(oppoLeft, oppoRight); //I took from right end

        return Math.max(leftEnd, rightEnd);
    }


    public static void main(String[] args) {
        int[] coins = {20, 30, 2, 2, 2, 10};
        System.out.println(optimalStrategyResult(coins, 0, coins.length - 1));
    }

}
