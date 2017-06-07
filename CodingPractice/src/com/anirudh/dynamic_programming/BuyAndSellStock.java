package com.anirudh.dynamic_programming;

/**
 * Created by paanir on 2/1/17.
 */
public class BuyAndSellStock {

    public static void main(String[] args) {

        int[] prices = {310, 315, 275, 295, 260, 270, 290, 230, 255, 250};

        int[] runningMinimum = new int[prices.length];
        int[] buyDays = new int[prices.length];

        int currMin = Integer.MAX_VALUE;

        //finding last minimum for each day
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] < currMin)
                buyDays[i] = i;
            else
                buyDays[i] = buyDays[i - 1]; //will never be the case for index 0 as index 0 will be caught by if
            if (prices[i] < currMin) {
                currMin = prices[i];
            }
            runningMinimum[i] = currMin;
        }

        int maxProfit = Integer.MIN_VALUE;

        int sellDay = 0;
        int buyDay = 0;
        //just subtracting that days prce from that days minimum nad finding max profit
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] - runningMinimum[i] > maxProfit) {
                sellDay = i;
                buyDay = buyDays[i];
                maxProfit = prices[i] - runningMinimum[i];
            }
        }

        System.out.println("Max profit of " + maxProfit + " obtained if stock bought at day " + buyDay + " and sold at day " + sellDay);
    }

}
