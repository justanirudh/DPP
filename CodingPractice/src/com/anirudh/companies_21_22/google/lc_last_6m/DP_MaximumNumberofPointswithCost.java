package com.anirudh.companies_21_22.google.lc_last_6m;

/*
1937. Maximum Number of Points with Cost
Medium

892

39

Add to List

Share
You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1),
picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.
 */
/*
Take from top or best until now
Similar to DP_MaximumSubarray

For every row, for every element, find the max sum that ends at that element
for that, find max from left elements, find max from right elems, and take max of left and right
 */
public class DP_MaximumNumberofPointswithCost {
    public long maxPoints(int[][] points) {
        int[] currTemp = points[0];

        long[] curr = new long[currTemp.length]; //change first row to long
        for(int i = 0; i < currTemp.length; ++i) {
            curr[i] = currTemp[i];
        }

        for (int i = 1; i < points.length; ++i) {

            long[] left = new long[curr.length]; //find max(s) from left side
            left[0] = curr[0];
            for (int j = 1; j < curr.length; ++j) {
                left[j] = Math.max(left[j - 1] - 1, curr[j]);
            }

            long[] right = new long[curr.length]; //find max(s) from right side
            right[curr.length - 1] = curr[curr.length - 1];
            for (int j = curr.length - 2; j >= 0; --j) {
                right[j] = Math.max(right[j + 1] - 1, curr[j]);
            }

            long[] next = new long[curr.length]; //find max overall and add to current row
            for(int j = 0; j < curr.length; ++j) {
                next[j] = points[i][j] + Math.max(left[j], right[j]);
            }

            curr = next; //this is now used for next row
        }

        long max = Integer.MIN_VALUE;
        for (long c : curr) {
            if (c > max)
                max = c;
        }
        return max;
    }
}
