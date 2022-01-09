package com.anirudh.companies_21_22.google.lc_last_6m;

/*
465. Optimal Account Balancing
Hard

1025

101

Add to List

Share
You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.

Return the minimum number of transactions required to settle the debt.



Example 1:

Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.


Constraints:

1 <= transactions.length <= 8
transactions[i].length == 3
0 <= fromi, toi <= 20
fromi != toi
1 <= amounti <= 100
Accepted
62,608

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Backtracking Brute Force
For every i, transfer all debt to any j > i and then find the minimum transactions for i->j transaction
Complexity:
f(n) = n * f(n-1) => O(n!)
 */
public class OptimalAccountBalancing {

    List<Integer> debtsArr;

    int settle(int i) {
        while (i < debtsArr.size() && debtsArr.get(i) == 0) { //ignore already settled people
            i++;
        }
        if (i == debtsArr.size())
            return 0; //reached end of arr
        int minTransactions = Integer.MAX_VALUE;
        for (int j = i + 1; j < debtsArr.size(); ++j) {
            if (debtsArr.get(i) * debtsArr.get(j) < 0) { //they are of opposing signs
                debtsArr.set(j, debtsArr.get(j) + debtsArr.get(i)); //give all of debt of i to j
                minTransactions = Math.min(minTransactions, 1 + settle(i + 1)); //calculate min, settle the next person
                debtsArr.set(j, debtsArr.get(j) - debtsArr.get(i)); //backtrack
            }
        }
        return minTransactions;
    }


    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> debts = new HashMap<>(); //person -> their debt
        //Get net debts
        for (int[] transaction : transactions) {
            int from = transaction[0];
            int to = transaction[1];
            int amt = transaction[2];
            debts.put(from, debts.getOrDefault(from, 0) - amt);
            debts.put(to, debts.getOrDefault(to, 0) + amt);
        }
        debtsArr = new ArrayList<>(debts.values());
        return settle(0); //get min settlements required; start settling from the 1st guy
    }
}
