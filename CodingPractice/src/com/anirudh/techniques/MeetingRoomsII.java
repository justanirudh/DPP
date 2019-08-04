package com.anirudh.techniques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by paanir on 8/4/19.
 */
/*
253. Meeting Rooms II
Medium

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:

Input: [[7,10],[2,4]]
Output: 1

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */
public class MeetingRoomsII {

    class Interval {
        int time;
        boolean isStart;

        Interval(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

    }

    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            int timeDiff = i1.time - i2.time;
            if (timeDiff != 0)
                return timeDiff;
            else {
                //if end and start times overlapping, then dont consider the meetings overlapping
                //Eg. [[6,8][8,10]] should require 1 room, not 2
                if (!i1.isStart && i2.isStart)
                    return -1;
                else if (i1.isStart && !i2.isStart)
                    return 1;
                else//both start or both end
                    return 0;
            }
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        List<Interval> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Interval(interval[0], true));
            list.add(new Interval(interval[1], false));
        }

        list.sort(new IntervalComparator());

        int count = 0, max = Integer.MIN_VALUE;

        for (Interval i : list) {
            if (i.isStart) {
                count++;
                max = Math.max(count, max);
            } else
                count--;
        }
        return max;
    }
}
