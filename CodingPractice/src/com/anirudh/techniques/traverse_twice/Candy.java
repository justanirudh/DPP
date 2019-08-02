package com.anirudh.techniques.traverse_twice;

/**
 * Created by paanir on 8/1/17.
 */
/*
135. Candy
Hard

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.



Similar to Trapping RainWater problem
 */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;

        int len = ratings.length;

        //L to R: if child's rating is more than her left neighbour's, she gets 1 more candy
        int[] candiesLR = new int[len];
        candiesLR[0] = 1;
        for (int i = 1; i < len; ++i) {
            if (ratings[i] > ratings[i - 1])
                candiesLR[i] = candiesLR[i - 1] + 1;
            else
                candiesLR[i] = 1; //minimum reqd
        }

        //R to L: if child's rating is more than her right neighbour's, she gets 1 more candy
        int[] candiesRL = new int[len];
        candiesRL[len - 1] = 1;
        for (int i = len - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1])
                candiesRL[i] = candiesRL[i + 1] + 1;
            else
                candiesRL[i] = 1; //minimum reqd
        }

        //find total
        int res = 0;
        for (int i = 0; i < len; ++i)
            res += Math.max(candiesLR[i], candiesRL[i]);

        return res;
    }
}
