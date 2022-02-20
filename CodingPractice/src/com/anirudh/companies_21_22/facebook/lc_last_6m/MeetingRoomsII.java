package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.List;

/*
253. Meeting Rooms II
Medium

5013

99

Add to List

Share
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1


Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
Accepted
569,711
Submissions
1,159,719
 */
/*
Sort start and end times. When its a start, count++, when end, count--. Return max count

Create points: {time, isStart}
Sort
    if time diff, sort by time diff
    if no time diff, end comes before start (same meeting room can be used)
Now iterate
    if start, count++, track max
    if end count--

TX: O(nlogn)
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


    public int minMeetingRooms(int[][] intervals) {
        List<Interval> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Interval(interval[0], true));
            list.add(new Interval(interval[1], false));
        }

        list.sort((a, b) -> {
            if (a.time != b.time) {
                return a.time - b.time;
            } else { //if same time, end comes before start
                if (!a.isStart && b.isStart) {
                    return -1;
                } else if (a.isStart && !b.isStart) {
                    return 1;
                } else
                    return 0;
            }
        });

        int curr = 0;
        int maxOverlaps = 0;
        for (Interval i : list) {
            if (i.isStart) {
                curr++;
                maxOverlaps = Math.max(maxOverlaps, curr);
            } else {
                curr--;
            }
        }
        return maxOverlaps;
    }
}
