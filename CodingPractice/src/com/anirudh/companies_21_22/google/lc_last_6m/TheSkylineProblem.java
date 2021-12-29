package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;

/*
218. The Skyline Problem
Hard

3470

183

Add to List

Share
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]



Example 1:


Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
Example 2:

Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]


Constraints:

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings is sorted by lefti in non-decreasing order.
Accepted
192,741
Submissions
507,008
 */

/*
https://www.youtube.com/watch?v=GSBLe8cKu0s

1. Sort the buildings based on these rules:
1.1 if x are diff, x1 < x2
1.2 if x are same
1.2.1 if both are start, h1 > h2
1.2.2 if both are end, h1 < h2
1.2.3 if one is start and other is end, s < e

2. Create a TreeMap of {height -> frequency of the height}
3. Iterate from sorted array and put them in treemap
4. if it is start, add to map; if it is end, remove from map
5. after addition/deletion from map, if newMaxHeight != prevMaxHeight
5.1 res.add(x, newMaxHeight)
 */
public class TheSkylineProblem {
    class Point {
        int x;
        boolean isStart;
        int height;

        Point(int x, boolean isStart, int height) {
            this.x = x;
            this.isStart = isStart;
            this.height = height;
        }
    }

    class ComparePoints implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.x != b.x) {
                return a.x - b.x;
            } else {
                if (a.isStart && b.isStart) { //both starts
                    return b.height - a.height; // decreasing order of height
                } else if (!a.isStart && !b.isStart) { //both ends
                    return a.height - b.height; //increasing order of height
                } else if (a.isStart) {
                    return -1; //put a before b
                } else
                    return 1;
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Point> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new Point(b[0], true, b[2]));
            points.add(new Point(b[1], false, b[2]));
        }
        points.sort(new ComparePoints());

        TreeMap<Integer, Integer> heights = new TreeMap<>(); //use treemap as need lastKey()
        heights.put(0, 1); //base case
        List<List<Integer>> res = new ArrayList<>();

        int prevMaxHeight = heights.lastKey(); //heighest key of the map = 0 right now
        for (Point p : points) { //go from left to right in points
            if (p.isStart) {
                heights.put(p.height, heights.getOrDefault(p.height, 0) + 1);
            } else {
                heights.put(p.height, heights.get(p.height) - 1);
                if (heights.get(p.height) == 0)
                    heights.remove(p.height);
            }
            int currMaxHeight = heights.lastKey();
            if(currMaxHeight != prevMaxHeight) { //less than or greater than
                res.add(Arrays.asList(p.x, currMaxHeight));
                prevMaxHeight = currMaxHeight;
            }
        }
        return res;
    }
}
