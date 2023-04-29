package com.anirudh.a_prep_2023.bloomberg;

import java.util.Arrays;
import java.util.Comparator;

/*
1029. Two City Scheduling
Medium
4.3K
320
company
Bloomberg
company
Microsoft
company
DoorDash
A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.

Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.



Example 1:

Input: costs = [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
Example 2:

Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
Output: 1859
Example 3:

Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
Output: 3086
 */

/*
extra price to pay for the company to send person i to A instead of B:
p[i][a] - p[i][b]
extra price to pay for the company to send person i to B instead of A:
p[i][b] - p[i][a]

1. sort by p[x][a] - p[x][b]
2. first n folks have least cost to go to A, last n folks have least cost to go to B (as they have most cost to go to A)
Sx: O(1)
Tx: O(nlogn)
*/

public class TwoCityScheduling {

    static class CompareACost implements Comparator<int[]> {
        public int compare(int[] a, int[] b) { //extra cost for person a versus extra cost for person b
            return (a[0] - a[1]) - (b[0] - b[1]);
        }
    }
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new CompareACost());
        int res = 0;
        for(int i = 0; i < costs.length/2; ++i) {
            res += costs[i][0]; //Add A's price
        }
        for (int i = costs.length/2; i < costs.length; ++i) {
            res += costs[i][1];
        }
        return res;
    }
}
