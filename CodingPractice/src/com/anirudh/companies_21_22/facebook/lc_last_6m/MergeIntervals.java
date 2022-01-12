package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
56. Merge Intervals
Medium

9381

430

Add to List

Share
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

/*
1. Sort by start times
2. if curr interval's end > next interval's start => new end is Max (curr end, next end)
 */
public class MergeIntervals {

    static class CompareIntervals implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new CompareIntervals());

        int start = intervals[0][0]; //1st elem
        int end = intervals[0][1];

        List<int[]> resL = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                int[] merged = {start, end};
                resL.add(merged); //closing this interval
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        int[] merged = {start, end};
        resL.add(merged); //closing last interval

        int[][] res = new int[resL.size()][2];
        for (int i = 0; i < resL.size(); ++i) {
            res[i] = resL.get(i);
        }
        return res;

    }
}
