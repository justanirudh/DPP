package com.anirudh.companies_21_22.facebook.lc_last_6m;

/*
1344. Angle Between Hands of a Clock
Medium

842

177

Add to List

Share
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.



Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5


Constraints:

1 <= hour <= 12
0 <= minutes <= 59
Accepted
82,134
Submissions
130,813
 */
/*
    public double angleClock(int hour, int minutes) {
        double hrHand = (hour % 12) + minutes/60.0;
        double hrAngle = hrHand * (360/12.0); //from x = 0 line
        double minAngle = minutes * (360/60.0); //from x = 0 line
        double angle = Math.abs(hrAngle - minAngle);
        return Math.min(angle, 360-angle);
    }
 */
public class AngleBetweenHandsofaClock {
    public double angleClock(int hour, int minutes) {
        double hrHand = (hour % 12) + minutes/60.0;
        double hrAngle = hrHand * (360/12.0); //from x = 0
        double minAngle = minutes * (360/60.0); //from x = 0
        double angle = Math.abs(hrAngle - minAngle);
        return Math.min(angle, 360-angle);
    }
}
