package com.anirudh.interview_prep_2021.two_sigma;

/*
188. Best Time to Buy and Sell Stock IV (atmost k transactions; cannot sell and buy on the same day)
Hard

2952

148

Add to List

Share
You are given an integer array prices where prices[i] is the price of a given stock on the ith day,
and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/A-Concise-DP-Solution-in-Java
And 1st explaination
Easy to understand explanation for the above solution

dp[i][j] = maximum profit from at most i transactions using prices[0..j]

A transaction is defined as one buy + sell.

Now on day j, we have two options

Do nothing (or buy) which doesn't change the acquired profit : dp[i][j] = dp[i][j-1]

Sell the stock: In order to sell the stock, you must've bought it on a day t=[0..j-1].
Maximum profit that can be attained is t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1])
where prices[j]-prices[t] is the profit from buying on day t and selling on day j.
dp[i-1][t-1] is the maximum profit that can be made with at most i-1 transactions with prices prices[0..t-1].

Time complexity of this approach is O(n2k).

In order to reduce it to O(nk), we must find t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) this expression in constant time. If you see carefully,

t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) is same as

prices[j] + t:0->j-1 max(dp[i-1][t-1]-prices[t])

Second part of the above expression maxTemp = t:0->j-1 max(dp[i-1][t-1]-prices[t]) can be included in
the dp loop by keeping track of the maximum value till j-1.

Base case:

dp[0][j] = 0; dp[i][0] = 0

DP loop:

for i : 1 -> k
    maxTemp = -prices[0];
    for j : 1 -> n-1
        dp[i][j] = max(dp[i][j-1], prices[j]+maxTemp);
        maxTemp = max(maxTemp, dp[i-1][j-1]-prices[j]);
return dp[k][n-1];

tmpMax means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices,
and buying the stock at price[j] - this is used for the next loop.
 */

/*

For 2 * k > num day
    BestTimeBuySellStockII
else
    below:

dp[i][j] = The maximum profit you can make from atmost i transactions in j days
k = 3 transactions
day = 6 days

d 0 1 2 3 4 5
k
0 0 0 0 0 0 0
1 0
2 0
3 0     j

On a day, we can either do (NOOP/Buy) OR Sell
dp[i,j] = Math.max(dp[i, j-1], sell) where dp[i, j-1] is the max profit with atmost i transactions at a previous day from j

sell -> for(int t = 0 to j-1) : Math.max(prices[j] - prices[t] + dp[i-1][t-1]) //For the ith transaction: p[j] - p[t] + profits until now dp[1 transaction less][1 day less]
 = prices[j] + {for(int t = 0 to j-1) : Math.max(- prices[t] + dp[i-1][t-1]) }

Combining all:
 dp[i,j] = Math.max(dp[i, j-1], prices[j] + {for(int t = 0 to j-1) : Math.max(-prices[t] + dp[i-1][t-1]) })
 */
public class DP_BestTimeBuySellStockIV {

    int[] prices;

    int maxProfitFromUnlimitedTransactions() { //cannot sell and buy on the same day
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0)
            return 0;
        this.prices = prices;
        if (2 * k >= prices.length) { //means we can do as many transactions as we like, not on the same day though
            return maxProfitFromUnlimitedTransactions();
        }

        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; ++i) { //for k = 0, all are 0
            int maxProfitUntilNow = -prices[0];  //base case, first day for kth transaction
            for (int j = 1; j < prices.length; ++j) { //cant do anything on first day, hence all are 0
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxProfitUntilNow);
                maxProfitUntilNow = Math.max(maxProfitUntilNow, dp[i - 1][j - 1] - prices[j]); //used for next iteration of j+1
            }
        }
        return dp[k][prices.length - 1];
    }
}
