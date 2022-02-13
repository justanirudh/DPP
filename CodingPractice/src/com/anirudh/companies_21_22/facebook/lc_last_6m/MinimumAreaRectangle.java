package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
939. Minimum Area Rectangle
Medium

1342

212

Add to List

Share
You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.



Example 1:


Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:


Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Constraints:

1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.
Accepted
93,026
Submissions
174,106
 */
/*
Similar to finding axis-aligned squares
O(n^2)
pair each elem(x,y) with other elem(a,b)
x!=a && y!=b for a valid rectangle they should be different
check if (x,b) and (a,y) in the set or not
find min area
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        int min = Integer.MAX_VALUE;
        Set<List<Integer>> set = new HashSet<>();
        for (int[] point : points) {
            set.add(Arrays.asList(point[0], point[1]));
        }
        for (int i = 0; i < points.length; ++i) {
            int[] p1 = points[i];
            for (int j = i + 1; j < points.length; ++j) {
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1]) //both should be diff to create a valid rectangle
                    continue;
                if (set.contains(Arrays.asList(p1[0], p2[1])) && set.contains(Arrays.asList(p2[0], p1[1]))) { //rectangle
                    min = Math.min(min, (Math.abs(p2[1] - p1[1])) * (Math.abs(p2[0] - p1[0])));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
