package com.anirudh.companies_21_22.google.lc_last_6m.anki;

/**
 * Created by paanir on 12/28/16.
 */
/*
134. Gas Station
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 */
public class GasStations {

    //This O(n^2) time, Brute force approach. O(n) time algo in EPI 17.6
    //O(n): https://leetcode.com/problems/gas-station/solution/
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        //consider starting from each index
        for (int startIndex = 0; startIndex < gas.length; ++startIndex) {

            int tank = 0;
            boolean canGo = true;
            for (int i = 0; i < gas.length; ++i) {
                int actualIndex = (startIndex + i) % gas.length; //circular list
                tank = tank + gas[actualIndex]; // total fuel in tank
                int tankLeft = tank - cost[actualIndex]; // fuel in tank after going the distance
                if (tankLeft < 0) {
                    canGo = false;
                    break;
                }
                tank = tankLeft; //if positive, tankLeft is new tank
            }
            if (canGo)
                return startIndex;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
