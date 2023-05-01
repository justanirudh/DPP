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

}
