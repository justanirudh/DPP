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
        if(ratings == null ||  ratings.length == 0)
            return 0;

        int[] candies = new int[ratings.length];
        candies[0] = 1;

        //L to R
        for(int i = 1; i < ratings.length; ++i){
            if(ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1;
            else
                candies[i] = 1; //minimum reqd
        }

        int res = candies[ratings.length - 1]; //start calculating the total number of candies from R to L

        //R to L
        for(int i = ratings.length - 2; i >= 0; --i){
            int curr = 1;
            if(ratings[i] > ratings[i+1]){
                curr = candies[i + 1] + 1;
            }
            res += Math.max(curr, candies[i]);  //get max of what was calculated before to what is calculated now
            candies[i] = curr; // this is a shortcut
            /*
            Ideal way would be:
             1. Create an array from L to R
             2. Create an array from R to L
             3. calculate result by summing up Max(first array[i], second array[i])
             */
        }

        return res;
    }
}
