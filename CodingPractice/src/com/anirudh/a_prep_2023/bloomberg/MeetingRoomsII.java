package com.anirudh.a_prep_2023.bloomberg;

import java.util.Arrays;
import java.util.Comparator;

/*
253. Meeting Rooms II
Medium
6.4K
136
company
Bloomberg
company
Amazon
company
Apple
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1


Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */

/*
find point of max overlap
sort start and end points individually, prioritizing end when tied
then +1 for start and -1 for end
find max
 */

/*
Sx = O(n)
Tx = O(nlogn)
 */
public class MeetingRoomsII {

    static class CompareElems implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0])
                return a[0] - b[0];
            else // first finish meeting then start new one to make sure less rooms reqd
                return a[1] - b[1];
        }

    }

    public int minMeetingRooms(int[][] intervals) {
        //open = 1, close = -1
        int[][] points = new int[intervals.length * 2][2];
        int j = 0;
        for (int[] interval : intervals) {
            points[j] = new int[]{interval[0], 1};
            ++j;
            points[j] = new int[]{interval[1], -1};
            ++j;
        }
        Arrays.sort(points, new CompareElems());
        int curr = 0;
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            curr += point[1];
            if (curr > max)
                max = curr;
        }
        return max;
    }
}
