package com.anirudh.dynamic_programming;

import java.util.HashMap;

/**
 * Created by paanir on 5/13/17.
 */
/*
322. Coin Change
You are given coins of different denominations and a total amount of money amount. Write a function to compute the
fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    HashMap<Integer, Integer> amountToNumber = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (amountToNumber.containsKey(amount))
            return amountToNumber.get(amount);

        int numCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            //selected this coin
            int currNumCoins = Integer.MAX_VALUE;

            if (amount >= coin) { //else, coin cannot be added
                int numOthers = coinChange(coins, amount - coin);
                if (numOthers != -1) //because if -1, then no combination found
                    currNumCoins = 1 + numOthers; //selected (coin) + numOthers
            }

            numCoins = Math.min(numCoins, currNumCoins);
        }

        int ret = (numCoins == Integer.MAX_VALUE)? -1 : numCoins;
        amountToNumber.put(amount, ret);
        return ret;
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();

        System.out.println(cc.coinChange(new int[]{1, 2, 5}, 11));
    }
}
