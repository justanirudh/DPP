package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by paanir on 9/2/21.
 */
/*
735. Asteroid Collision
Medium

2359

189

Add to List

Share
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.


Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
Example 4:

Input: asteroids = [-2,-1,1,2]
Output: [-2,-1,1,2]
Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 */

/*
Same logic as open and closed parenthesis. Make sure to look at all cases but the basic algo is same
+ve asteroids are open paren, -ve asteroids are closed parens
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty() || stack.peek() < 0 || asteroid > 0) { //asteroid is +ve OR stack.peek is -ve
                stack.push(asteroid);
            } else { //asteroid is -ve and stack.peek is +ve
                if (Math.abs(asteroid) > stack.peek()) {
                    while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(asteroid) > stack.peek()) { // 3 conditions
                        stack.pop();
                    }
                    if (stack.isEmpty() || stack.peek() < 0) { //if the first 2 conditions were violated
                        stack.push(asteroid);
                    } else if (Math.abs(asteroid) == stack.peek()) { //if the last condition was violated
                        stack.pop();
                    } else { // Math.abs(asteroid) < stack.peek() //if the last condition was violated
                        //NOOP, as the asteroid gets destroyed by stack.peek
                    }
                } else if (Math.abs(asteroid) == stack.peek()) {
                    stack.pop();
                } else { //Math.abs(asteroid) < stack.peek()
                    //NOOP
                }
            }

        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; --i) {
            res[i] = stack.pop();
        }
        return res;
    }
}
