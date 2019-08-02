package com.anirudh.techniques.stacks;

import java.util.Stack;

/**
 * Created by paanir on 12/11/17.
 */
/*
739. Daily Temperatures
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would
have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {

    //https://leetcode.com/problems/daily-temperatures/solution/ last approach
    //O(n) space, O(n) time
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>(); //stack of indices. Stack will only have increasing temperatures from top to bottom
        int len = temperatures.length;
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; --i) { //starting from the end as it is the base case (0 would be the answer for the last elem)
            int curr = temperatures[i];
            if (stack.isEmpty()) {
                res[i] = 0;
                stack.push(i);
            } else if (curr < temperatures[stack.peek()]) { //if curr elem < elem for index at top of stack
                res[i] = stack.peek() - i;
                stack.push(i);
            } else { //if curr elem >= elem for index at top of stack
                while (!stack.isEmpty() && curr >= temperatures[stack.peek()]) {
                    stack.pop();
                }
                if (stack.isEmpty())
                    res[i] = 0; //biggest elem. no day after this day will have a higher temp. hence 0
                else
                    res[i] = stack.peek() - i;
                stack.push(i);
            }
        }
        return res;
    }

    //O(n^2) time O(1) space
    public int[] dailyTemperaturesBad(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        int i;
        for (i = 0; i < len - 1; ++i) {
            int curr = temperatures[i];
            for (int j = i + 1; j < len; ++j) {
                int next = temperatures[j];
                if (next > curr) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        res[i] = 0;
        return res;
    }

}
