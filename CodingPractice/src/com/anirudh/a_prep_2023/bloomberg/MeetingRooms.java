package com.anirudh.a_prep_2023.bloomberg;

import java.util.Arrays;
import java.util.Comparator;

/*
252. Meeting Rooms
Easy
1.8K
87
company
Google
company
Bloomberg
company
Amazon
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
* */
        /*
    1. sort by start times
    2. If start of next < end of prev, return false
    */

public class MeetingRooms {
    static class CompareElems implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        }
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0 || intervals.length == 1)
            return true;
        Arrays.sort(intervals, new CompareElems());
        for (int i = 1; i < intervals.length; ++i) {
            int[] prev = intervals[i - 1];
            int[] curr = intervals[i];
            if (curr[0] < prev[1])
                return false;
        }
        return true;

    }
}
