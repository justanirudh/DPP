package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/28/21.
 */
//528. Random Pick with Weight

import java.util.Random;

/**
 * 528. Random Pick with Weight
 * Medium
 * <p>
 * 1431
 * <p>
 * 3045
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 * <p>
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3],
 * the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking
 * the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 * <p>
 * More formally, the probability of picking index i is w[i] / sum(w).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
 * Example 2:
 * <p>
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.
 * <p>
 * Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 */

/**
 * IMPORTANT
 *
 * Catch: Create a runningSum array from the weights array
 * The range of numbers between runningSums rs1 and rs2 will be basket of rs2 where a random number can fall
 * Basket of first number rs0 will [0,rs0)
 *
 * For [1,3]
 * runningSum = [1,4]
 * 0 index's field: [0,1)
 * 1 index's field: [1,2,3,4)
 *
 * Then for the pickIndex, find a random number from 0 to sumOfAllWeights and
 * do a binary search for it in the runningSum array
 *
 * https://leetcode.com/problems/random-pick-with-weight/solution/
 */

public class RandomPickwithWeight {
    int[] runningSums;
    Random rand = new Random();

    private int binarySearch(double num, int start, int end) {
        int res = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (num == runningSums[mid])
                return mid + 1;// the index itself belongs to next number's field as field of index is [)
            else if (num < runningSums[mid]) { //potential solution
                res = mid;
                end = mid - 1;
            } else // weight < num
                start = mid + 1;
        }
        return res;
    }

    public RandomPickwithWeight(int[] w) {
        runningSums = new int[w.length];
        runningSums[0] = w[0];
        for (int i = 1; i < w.length; ++i) {
            runningSums[i] = runningSums[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int maxSum = runningSums[runningSums.length - 1];
        int num = rand.nextInt(maxSum);
        //find num's potential index in runningSums array
        return binarySearch(num, 0, runningSums.length - 1);

    }

    // Memory limit exceeded
    // For each weight, add the index that many number of times in an array
    // Then shuffle that array
    // Then pick a random number from it

//    int[] res;
//    Random rand2 = new Random();
//
//    void swap(int i, int j) {
//        int temp = res[i];
//        res[i] = res[j];
//        res[j] = temp;
//    }
//
//    void randomizeArray() {
//
//        for(int i = 1; i < res.length; ++i) {
//            int swapIdx = rand.nextInt(i); //gives me a random number between 0 and i - 1; swap this with i
//            swap(swapIdx, i);
//        }
//        //can also use inbuilt Collections.shuffle method
//        Collections.shuffle(Arrays.asList(res));
//    }
//
//    public RandomPickwithWeight2(int[] w) {
//        int sum = 0;
//        for(int weight : w) {
//            sum += weight;
//        }
//        res = new int[sum];
//        int r = 0;
//        for(int i = 0; i <w.length; ++i) {
//            int weight = w[i];
//            for(int j = 0; j < weight; ++j) {
//                res[r] = i;
//                r++;
//            }
//        }
//        randomizeArray(); //Do it yourself
//    }
//
//    public int pickIndex2() {
//        return res[rand.nextInt(res.length)];
//
//    }
}
