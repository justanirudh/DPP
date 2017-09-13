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

    HashMap<Integer, Integer> amountToNumber = new HashMap<>(); //amount to minimum number of coins reqd

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        if (amountToNumber.containsKey(amount))
            return amountToNumber.get(amount);

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {//else, coin cannot be added
                int num = coinChange(coins, amount - coin); //selected the coin; if number of coins not infinite, pass the left number of coins
                if (num != -1)//because if -1, then no combination found
                    res = Math.min(num + 1, res);// + 1 because selected (coin) + numOthers
            }
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        amountToNumber.put(amount, res);
        return res;
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();

        System.out.println(cc.coinChange(new int[]{1, 2, 5}, 11));
    }
}
