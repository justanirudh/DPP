package com.anirudh.dynamic_programming_greedy.greedy;

import java.util.*;

/**
 * Created by paanir on 6/23/17.
 */
/*
56. Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 */
/*
    //1. SORT by STARTING TIMES (vs MinimumNumberOfArrows/Activity selection where sort by finish times)
    //2. if next's start < current's end, take max of current's end and next's end
    //sorting by end times and taking min of start times doesnt work
*/
public class MergeIntervals {

    public class Interval {
      int start;
      int end;
      Interval(int s, int e) { start = s; end = e; }
  }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        intervals.sort(Comparator.comparingInt(i -> i.start));

        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end); //take max end of overlapping intervals
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }
}
