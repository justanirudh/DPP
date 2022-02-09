package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.Arrays;
import java.util.Comparator;

/*
252. Meeting Rooms
Easy

1258

61

Add to List

Share
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106
Accepted
242,755
Submissions
429,569
 */
/*
sort
start of next should be >= end of previous
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0])); //compare start times
        for(int i= 0; i < intervals.length - 1; ++i) {
            if(intervals[i][1] > intervals[i + 1][0]) { //prev's end time > curr's start time
                return false;
            }
        }
        return true;
    }
}
