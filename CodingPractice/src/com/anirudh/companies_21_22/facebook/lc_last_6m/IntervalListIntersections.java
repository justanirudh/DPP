package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
986. Interval List Intersections
Medium

3851

79

Add to List

Share
You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].



Example 1:


Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []


Constraints:

0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 109
endi < starti+1
0 <= startj < endj <= 109
endj < startj+1
 */

/*
1. merge the 2 lists in O(n) by start times
2. Next do merge intervals like so:
    if (end of previous >= start of next)
        resL.add([start, min(prev_end, curr_end)])
        new_end = max(prev_end, curr_end)
    else
        new_end = end of curr elem
 */
public class IntervalListIntersections {
    int[][] merge(int[][] first, int[][] second) { //no need to pass tags for each arr
        int[][] res = new int[first.length + second.length][2];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < first.length || j < second.length) {
            int firstI = (i < first.length) ? first[i][0] : Integer.MAX_VALUE;
            int secondJ = (j < second.length) ? second[j][0] : Integer.MAX_VALUE;
            if (firstI < secondJ) {
                res[k] = first[i];
                i++;
            } else {
                res[k] = second[j];
                j++;
            }
            k++;
        }
        return res;
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int[][] merged = merge(firstList, secondList); //sorted by start times
        List<List<Integer>> resL = new ArrayList<>();
        int end = merged[0][1];
        for (int i = 1; i < merged.length; ++i) {
            if (end >= merged[i][0]) { //end of last >= start of next
                int smallerEnd = Math.min(end, merged[i][1]);
                List<Integer> intersect = Arrays.asList(merged[i][0], smallerEnd);
                resL.add(intersect);
                end = Math.max(end, merged[i][1]);
            } else {
                end = merged[i][1];
            }
        }
        int[][] res = new int[resL.size()][2];
        for (int i = 0; i < resL.size(); ++i) {
            res[i][0] = resL.get(i).get(0);
            res[i][1] = resL.get(i).get(1);
        }
        return res;
    }
}
