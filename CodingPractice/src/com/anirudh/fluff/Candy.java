package com.anirudh.fluff;

/**
 * Created by paanir on 8/1/17.
 */
/*
135. Candy

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Similar to Tap Water problem
 */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;

        int[] candiesLR = new int[ratings.length];
        candiesLR[0] = 1;

        //L to R
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i - 1])
                candiesLR[i] = candiesLR[i - 1] + 1;
            else
                candiesLR[i] = 1; //minimum reqd
        }

        //R to L
        int[] candiesRL = new int[ratings.length];
        candiesRL[ratings.length - 1] = candiesLR[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1])
                candiesRL[i] = candiesRL[i + 1] + 1;
            else
                candiesRL[i] = 1; //minimum reqd
        }

        //find total
        int res = 0;
        for (int i = 0; i < ratings.length; ++i)
            res += Math.max(candiesLR[i], candiesRL[i]);

        return res;
    }
}
