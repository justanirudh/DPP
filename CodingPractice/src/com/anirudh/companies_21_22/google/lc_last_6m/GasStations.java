package com.anirudh.companies_21_22.google.lc_last_6m;

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

/*
Option 1: Tx: O(n^2) Brute Force
Start from each gas station and check if you can go around 1 circle clockwise

Option 2: Tx: O(n)
1. sum_all(gas) >= sum_all(cost)
2. at any gas station i, if gas(i) < cost(i), it cannot be starting station
Start from startStation = 0, keep building totalGas and currGas as gas(i) - cost(i)
if at any point, currGas(i) < 0 => currGas = 0 and startStation = i + 1 (as cost(i) is to go from i to i + 1)
return startStation at the end

Why it works?
startStation to 0 is proven by algo
What about 0 to startStation?
Proof here (easy): https://leetcode.com/problems/gas-station/solution/
 */
public class GasStations {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totGasLeft = 0;
        int currGasLeft = 0;
        int startStation = 0;
        for (int i = 0; i < gas.length; ++i) {
            totGasLeft += gas[i] - cost[i];
            currGasLeft += gas[i] - cost[i];
            if (currGasLeft < 0) {
                currGasLeft = 0;
                startStation = i + 1;
            }
        }
        return totGasLeft >= 0 ? startStation : -1;
    }

    public static int canCompleteCircuitSlow(int[] gas, int[] cost) {

        //consider starting from each index
        for (int startIdx = 0; startIdx < gas.length; ++startIdx) {
            int tank = 0;
            int i = 0;
            for (; i < gas.length; ++i) {
                int actualIdx = (startIdx + i) % gas.length; //circular list
                tank += gas[actualIdx] - cost[actualIdx]; // total fuel in tank - cost of going until next stop
                if (tank < 0) //if at any point tank < 0, break
                    break;
            }
            if (i == gas.length)
                return startIdx;
        }
        return -1;
    }

}
