package com.anirudh.companies_21_22.google.lc_last_6m;
/*
1499. Max Value of Equation
Hard

722

27

Add to List

Share
You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.



Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.


Constraints:

2 <= points.length <= 105
points[i].length == 2
-108 <= xi, yi <= 108
0 <= k <= 2 * 108
xi < xj for all 1 <= i < j <= points.length
xi form a strictly increasing sequence.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
yi + yj + |xi - xj| = yi + yj + xj - xi (as xj > xi for j < i)
= (yi-xi) + (yj + xj)

Sliding window problem: need to keep the window such that xj - xi <= k

Deque has int[xi, (yi-xi)].
    1st elem is used to remove elem from head such that (xj-xi) remains <=k
    2nd elem is used to remove elems from tail such that we have decreasing order of (yi-xi) from start to end

Deque has elems in decreasing order of yi-xi from head to tail

when a new elem comes in: xj,yj
1. remove elems from head until xj-xi becomes <=k
2. find max = Max(max, head[1]+ xj + yj ) //deque has elems as decreasing order of yi-xi, so head will have largest elem in window
3. remove elems from tail until you find an elem such that elem[1] > yj-xj
 */
public class MaxValueofEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<List<Integer>> richPoints = new ArrayDeque<>(); //int[xi, (yi-xi)]
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            //first remove all that are no longer valid from head
            while (!richPoints.isEmpty() && point[0] - richPoints.peekFirst().get(0) > k) { // while xj-xi > k
                richPoints.pollFirst();
            }
            if (!richPoints.isEmpty()) {
                max = Math.max(max, richPoints.peekFirst().get(1) + point[0] + point[1]); // (yi-xi) + xj + yj
            }
            //remove smaller elems(yi-xi) from tail
            while (!richPoints.isEmpty() && richPoints.peekLast().get(1) < point[1] - point[0]) { //if peekLast(yi-xi) < yj-xj, remove
                richPoints.pollLast();
            }
            richPoints.offer(Arrays.asList(point[0], point[1] - point[0])); //add {xi, yi-xi}
        }
        return max;
    }
}
