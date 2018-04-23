package com.anirudh.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 10/16/17.
 */
/*
447. Number of Boomerangs
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k)
such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range
[-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        //For any point, find all points that are at the same distance from that point

        int count = 0;
        for (int i = 0; i < points.length; ++i) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; ++j) {
                double dist = Math.pow(points[j][0] - points[i][0], 2) + Math.pow(points[j][1] - points[i][1], 2);
                if (!map.containsKey(dist))
                    map.put(dist, 0);
                map.put(dist, map.get(dist) + 1);
            }
            for (int numNeigh : map.values()) {
                count += numNeigh * (numNeigh - 1); //first point fixed: 1 * num ways to fill it * num-1 ways to fill it
            }
        }
        return count;
    }
}
