package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 6/3/17.
 */
//Leetcode: #121 under-construction

public class BestTimetoBuyandSellStock {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int maxProfit;
        int[] maxProfits = new int[];
        int buy = prices[0];
        for(int i = 0; i < prices.length; ++i){
            int sell = prices[i];

        }
        return maxProfit;
    }
}
