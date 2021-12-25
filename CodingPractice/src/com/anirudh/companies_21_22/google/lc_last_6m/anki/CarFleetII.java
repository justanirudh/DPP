package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet.
The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.



Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]
 */

//---------------1.
// as soon as a car c1 catches another car c2, we can say c1 vanishes into c2; meaning that
// after catching c2, we may view c1 as non-existing (cars before c1 may not catches c1);

/**
 * Define a stack storing the index of cars as follows:
 * <p>
 * Assuming cars c_0, c_1, c_2, ... , c_k are in the stack, they satisfy:
 * 1. v0 > v1 > v2 ... > vk where v_i is the velocity of car c_i
 * 2. c_(i+1) is the car that c_i vanishes into
 * <p>
 * Namely, if only these cars exist, then what will happened is that c_0 vanishes into c_1,
 * then c_1 vanishes into c_2, ...,  c_(k-1) vanishes into c_k;
 * If both conditions are satisfied:
 * 1. c1 is faster than c2
 * 2. c1 catches c2 before c2 vanishes into other car
 * <p>
 * Then we know that c2 is the car that c1 catches first (i.e., c1 vanishes into c2)
 * ==> get the result for c1
 * <p>
 * Note neither c1 nor c2 is polled out from the stack considering the rule of stack.
 * If both conditions are satisfied:
 * 1. c1 is faster than c2
 * 2. c1 catches c2 before c2 vanishes into other car
 * <p>
 * Then we know that c2 is the car that c1 catches first (i.e., c1 vanishes into c2)
 * ==> get the result for c1
 * <p>
 * Note neither c1 nor c2 is polled out from the stack considering the rule of stack.
 * If both conditions are satisfied:
 * 1. c1 is faster than c2
 * 2. c1 catches c2 before c2 vanishes into other car
 * <p>
 * Then we know that c2 is the car that c1 catches first (i.e., c1 vanishes into c2)
 * ==> get the result for c1
 * <p>
 * Note neither c1 nor c2 is polled out from the stack considering the rule of stack.
 */

//-----------------2

/** If both conditions are satisfied:
 1. c1 is faster than c2
 2. c1 catches c2 before c2 vanishes into other car

 Then we know that c2 is the car that c1 catches first (i.e., c1 vanishes into c2)
 ==> get the result for c1

 Note neither c1 nor c2 is polled out from the stack considering the rule of stack.
 */

//--------------------------3

/** Now we have either one of situations
 1. c1 is slower than c2
 2. c1 potentially catches c2 AFTER c2 vanishes

 Claim: no car before c1 will vanish into c2

 1. ==> cars before c1 will vanish into c1 first before catching c2
 2. <==> c2 "vanishes" into another car even before c1 catches it


 Either way, c2 can not be catched by c1 or cars beofre c1 ==> poll it out from stack

 */

/*
Create a stack of decreasing speeds from top to bottom of stack.
Traverse from right to left.
By default, initialize result array with all -1s
For every entry pushed also update the result array

If the current car "c's" speed is greater than next car "nc" in the stack && the nc's result array entry (time to collide for nc and nnc)
is GREATER than the time to collide for c and nc then,
    push in stack and update res[c] with car collide time
else
    if the above is not true, pop out the peek car and check if c can collide with the resultant fleet of nc+nnc

collide time = relative distance/ relative speed

 */

public class CarFleetII {

    int[][] cars;

    double catchTime(int curr, int next) {
        int relSpeed = cars[curr][1] - cars[next][1];
        int relDistance = cars[next][0] - cars[curr][0];
        return relDistance * 1.0 / relSpeed;
    }

    public double[] getCollisionTimes(int[][] cars) {
        double[] res = new double[cars.length];
        this.cars = cars;
        Deque<Integer> collisions = new ArrayDeque<>(); //cars with decreasing speeds from top to bottom
        for (int i = cars.length - 1; i >= 0; --i) { //cars already sorted according to their position
            res[i] = -1; //default
            while (!collisions.isEmpty()) {
                int currSpeed = cars[i][1];
                int nextIdx = collisions.peek();
                int nextSpeed = cars[nextIdx][1];
                double currCatchTime = catchTime(i, nextIdx);
                if (currSpeed > nextSpeed && (res[nextIdx] == -1 || currCatchTime <= res[nextIdx])) { //if curr speed > nextSpeed && currCatchTime < next's catch time
                    res[i] = currCatchTime;
                    break;
                } //else if currSpeed < nextSpeed || currCatchTime >= res[nextIdx]
                collisions.pop(); //it can still collide with the combined fleet after the next one merges with its' next
            }
            collisions.push(i);
        }
        return res;
    }
}
