package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by paanir on 9/6/21.
 */
/*
346. Moving Average from Data Stream
Easy

989

99

Add to List

Share
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.


Example 1:

Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 */

/*
Use a deque to store elems. for every next do
            sum -= elems.poll();
            return sum * 1.0 /size;
 */
public class MovingAverageDataStream {

    Deque<Integer> elems; //always of size "size"
    int size;
    int sum;
    int currSize;


    /** Initialize your data structure here. */
    public MovingAverageDataStream(int size) {
        elems = new ArrayDeque<>();
        this.size = size;
        sum = 0;
        currSize = 0;
    }

    public double next(int val) {
        sum += val;
        currSize++;
        elems.offer(val);

        if(currSize <= size) {
            return sum * 1.0 /currSize;
        }
        else {
            sum -= elems.poll();
            return sum * 1.0 /size;

        }
    }
}
