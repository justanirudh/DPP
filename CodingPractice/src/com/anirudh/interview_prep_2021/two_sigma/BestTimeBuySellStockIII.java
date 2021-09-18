package com.anirudh.interview_prep_2021.two_sigma;

/*
123. Best Time to Buy and Sell Stock III (buy and sell atmost 2 times)
Hard

4336

96

Add to List

Share
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging
multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
Example 4:

Input: prices = [1]
Output: 0
 */

/*
 Reinvest profit from 1st greedy maxProfit
 */
public class BestTimeBuySellStockIII {
    public int maxProfit(int[] prices) {
        int minFirstCost = Integer.MAX_VALUE;
        int maxFirstProfit = 0;
        int minSecondCost = Integer.MAX_VALUE;
        int maxTotalProfit = 0;

        for(int i = 0; i < prices.length; ++i) { //first 2 lines are for solving problem if only 1 transaction is allowed
            minFirstCost = Math.min(minFirstCost, prices[i]);
            maxFirstProfit = Math.max(maxFirstProfit, prices[i] - minFirstCost);
            //reinvest profit in 2nd stock
            minSecondCost = Math.min(minSecondCost, prices[i] - maxFirstProfit);
            maxTotalProfit = Math.max(maxTotalProfit, prices[i] - minSecondCost);

        }
        return maxTotalProfit;
    }
}
