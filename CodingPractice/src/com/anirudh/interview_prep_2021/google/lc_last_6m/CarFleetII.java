package com.anirudh.interview_prep_2021.google.lc_last_6m;

import com.anirudh.general_algos.basics.LinkedList;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by paanir on 9/4/21.
 */
/*
1776. Car Fleet II
Hard

372

7

Add to List

Share
There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:

positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
speedi is the initial speed of the ith car in meters per second.
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.



Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]
 */
public class CarFleetII {

    public double[] getCollisionTimes(int[][] cars) {

        double[] res = new double[cars.length];
        Arrays.fill(res, -1.0);

        // as soon as a car c1 catches another car c2, we can say c1 vanishes into c2; meaning that
        // after catching c2, we may view c1 as non-existing (cars before c1 may not catches c1);

        /** Define a stack storing the index of cars as follows:

         Assuming cars c_0, c_1, c_2, ... , c_k are in the stack, they satisfy:
         1. v0 > v1 > v2 ... > vk where v_i is the velocity of car c_i
         2. c_(i+1) is the car that c_i vanishes into

         Namely, if only these cars exist, then what will happened is that c_0 vanishes into c_1,
         then c_1 vanishes into c_2, ...,  c_(k-1) vanishes into c_k;
         */
        Deque<Integer> stack = new ArrayDeque<>();
        for (int currIdx = cars.length - 1; currIdx >= 0; currIdx--) {
            int[] currCar = cars[currIdx];

            while (!stack.isEmpty()) {
                int peekIdx = stack.peekFirst();
                int[] peekCar = cars[peekIdx];

                /** If both conditions are satisfied:
                 1. c1 is faster than c2
                 2. c1 catches c2 before c2 vanishes into other car

                 Then we know that c2 is the car that c1 catches first (i.e., c1 vanishes into c2)
                 ==> get the result for c1

                 Note neither c1 nor c2 is polled out from the stack considering the rule of stack.
                 */

                if (currCar[1] > peekCar[1] && (res[peekIdx] == -1.0 || catchTime(cars, currIdx, peekIdx) <= res[peekIdx])) {
                    res[currIdx] = catchTime(cars, currIdx, peekIdx);
                    break;
                }

                /** Now we have either one of situations
                 1. c1 is slower than c2
                 2. c1 potentially catches c2 AFTER c2 vanishes

                 Claim: no car before c1 will vanish into c2

                 1. ==> cars before c1 will vanish into c1 first before catching c2
                 2. <==> c2 "vanishes" into another car even before c1 catches it


                 Either way, c2 can not be catched by c1 or cars beofre c1 ==> poll it out from stack

                 */
                stack.removeFirst();
            }
            stack.addFirst(currIdx);
        }
        return res;
    }


    // time for cars[i] to catch cars[j]
    private double catchTime(int[][] cars, int i, int j) {
        int dist = cars[j][0] - cars[i][0]; //rel dist
        int v = cars[i][1] - cars[j][1];// rel speed

        return (double) dist / v;
    }
}
