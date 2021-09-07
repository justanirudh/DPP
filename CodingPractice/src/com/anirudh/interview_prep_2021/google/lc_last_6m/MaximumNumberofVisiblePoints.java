package com.anirudh.interview_prep_2021.google.lc_last_6m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paanir on 9/2/21.
 */
/*
1610. Maximum Number of Visible Points
Hard

243

320

Add to List

Share
You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.

Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].


You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.

There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.

Return the maximum number of points you can see.



Example 1:


Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
Output: 3
Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
Example 2:

Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
Output: 4
Explanation: All points can be made visible in your field of view, including the one at your location.
Example 3:


Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
Output: 1
Explanation: You can only see one of the two points, as shown above.
 */

/*
1. For each point, find the angle between (line joining the point and the person) with the x axis
2. At any time, person can only view points in "angle" field of view
 3. Sort all angles. Add 360 to each angle and add it to sorted array. This is so that I can go circular back
 4. Do a sliding window in the sorted array. Start from 1st degree, expand 2nd degree until it is greater than "angle".
 Keep recording points. After the angle is greater, increment the smaller angle and then keep incrementing larger angle
 until it crosses "angle". So on and so forth
 */

public class MaximumNumberofVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

        //first find angles between {point to location} and x axis
        List<Double> angles = new ArrayList<>();
        int same = 0;

        for (List<Integer> point : points) {
            int dx = location.get(0) - point.get(0);
            int dy = location.get(1) - point.get(1);
            if (dx == 0 && dy == 0) {
                same++; //same point so always in field of view
                continue;
            }
            double currAngle = Math.atan2(dy, dx) * 180 / Math.PI; // (in radians) * 180/pi = in degrees
            angles.add(currAngle);
        }

        Collections.sort(angles);

        //Now do sliding window to find max points in field of view
        List<Double> circularAngles = new ArrayList<>(angles); // to accept circular cases
        for (Double ang : angles) {
            circularAngles.add(ang + 360);
        }

        int res = 0;
        int slow = 0;
        for (int fast = 0; fast < circularAngles.size(); fast++) { //fast goes till end
            while (circularAngles.get(fast) - circularAngles.get(slow) > angle) {
                slow++; //when angle becomes greater, increment slow such that angle becomes less again
            }
            res = Math.max(res, fast - slow + 1); //this gets called directly while fast - slow < angle
        }

        return res + same;

    }
}
