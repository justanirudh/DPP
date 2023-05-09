package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
1169. Invalid Transactions
Medium
445
2.1K
company
Bloomberg
company
Amazon
company
Wix
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.

Return a list of transactions that are possibly invalid. You may return the answer in any order.



Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
 */

/*
TODO
Go over transactions array, Make map of person -> transactions
Now go over transaction array again, get the name, get all transactions for the name from the map,
then compare each transaction

Sx: O(n)
Tx: O(n^2)
 */
public class InvalidTransactions {
    public List<String> invalidTransactions(String[] transactions) {
        /*
        0: name
        1: time
        2: amount
        3: city
        */

        //{name -> {city -> [[time, amt]]}}
        Map<String, Map<String, List<Integer>>> transM = new HashMap<>();
        for (String trans : transactions) {
            String[] props = trans.split(",");
            transM.putIfAbsent(props[0], new HashMap<>()); // {name -> {}}
            transM.get(props[0]).putIfAbsent(props[3], new ArrayList<>()); //{name -> {city -> time}}
            transM.get(props[0]).get(props[3]).add(Integer.parseInt(props[1]));
        }

        List<String> res = new ArrayList<>();
        for (String trans : transactions) {
            boolean found = false;
            String[] props = trans.split(",");
            if (Integer.parseInt(props[2]) > 1000) { //add it but should be used to check others
                res.add(trans);
                continue;
            }
            Map<String, List<Integer>> cityTimes = transM.get(props[0]);
            String currCity = props[3];
            int currTime = Integer.parseInt(props[1]);
            for (String city : cityTimes.keySet()) {
                if (!city.equals(currCity)) { //ignore same city trans
                    List<Integer> times = cityTimes.get(city);
                    for (int t : times) {
                        if (Math.abs(t - currTime) <= 60) { //add both trans
                            res.add(trans); //orig trans, not puttin other trans to avoid duplication when we process it
                            found = true;
                            break;
                        }
                    }
                    if (found)
                        break;
                }
            }
        }
        return res;
    }

}
