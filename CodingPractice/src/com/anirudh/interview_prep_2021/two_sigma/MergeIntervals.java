package com.anirudh.interview_prep_2021.two_sigma;

import java.util.*;

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

    class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    class CompareIntervals implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

    public int[][] merge(int[][] intervals) {
        List<Interval> l = new ArrayList<>();
        for (int[] i : intervals) {
            l.add(new Interval(i[0], i[1]));
        }
        l.sort(new CompareIntervals());
        int start = l.get(0).start;
        int end = l.get(0).end;

        List<Interval> resL = new ArrayList<>();
        for (int i = 1; i < l.size(); ++i) {
            if (l.get(i).start <= end) {
                end = Math.max(end, l.get(i).end);
            } else {
                resL.add(new Interval(start, end));
                start = l.get(i).start;
                end = l.get(i).end;
            }
        }
        resL.add(new Interval(start, end));
        int[][] res = new int[resL.size()][2];
        for (int i = 0; i < resL.size(); ++i) {
            res[i][0] = resL.get(i).start;
            res[i][1] = resL.get(i).end;
        }
        return res;

    }
}
