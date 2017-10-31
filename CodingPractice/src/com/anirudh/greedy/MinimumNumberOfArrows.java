package com.anirudh.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by paanir on 4/2/17.
 */
/*
452. Minimum Number of Arrows to Burst Balloons

There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start
and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates
of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts
 by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot
  keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6])
and another arrow at x = 11 (bursting the other two balloons).

 */
public class MinimumNumberOfArrows {

    //Activity Selection Problem
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        //SORT points by ending index OR FINISH times (vs sorting by start times in mergeintervals problem)
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int ret = Integer.compare(a[1], b[1]);
                if (ret != 0)
                    return ret;
                else
                    return Integer.compare(a[0], b[0]);
            }
        });
        //Greedy approach: take first activity. then take next activity who's starting time is after current activity's finishing time
        int startIndex = 0;
        int res = 0;
        int i = 0;
        while (i < points.length) {
            int endingIndex = points[startIndex][1];
            res++;
            while (i < points.length && points[i][0] <= endingIndex) {
                i++;
            }
            if (i == points.length)
                break;
            startIndex = i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] array = {{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}};

        System.out.println(findMinArrowShots(array));
    }
}
