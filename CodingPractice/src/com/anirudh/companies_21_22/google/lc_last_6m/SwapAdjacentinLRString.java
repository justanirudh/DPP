package com.anirudh.companies_21_22.google.lc_last_6m;
/*
777. Swap Adjacent in LR String
Medium

677

610

Add to List

Share
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing
one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR".
Given the starting string start and the ending string end,
return True if and only if there exists a sequence of moves to transform one string to the other.



Example 1:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: true
Explanation: We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Example 2:

Input: start = "X", end = "L"
Output: false


Constraints:

1 <= start.length <= 104
start.length == end.length
Both start and end will only consist of characters in 'L', 'R', and 'X'.
Accepted
45,957
Submissions
127,791
 */

/*
Insights:
1. all the non-X chars relative position remains the same
2. For an L in start vs end string, pos(L) in start >= pos(L) in end
3. For an R in start vs end string, pos(R) in start <= pos(R) in end
 */
public class SwapAdjacentinLRString {
    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))) //from 1
            return false;
        int s = 0;
        int e = 0;
        while (s < start.length() && e < end.length()) {
            while (s < start.length() && start.charAt(s) == 'X') //find first non-X char in start
                s++;
            while (e < end.length() && end.charAt(e) == 'X') //find first non-X char in start
                e++;
            if (s == start.length() && e == end.length()) //if both reach end
                return true;
            if (s == start.length() || e == end.length()) { //if either one reaches end
                return false;
            }
            if (start.charAt(s) != end.charAt(e)) // from 1
                return false;
            if (start.charAt(s) == 'L' && s < e) //from 2
                return false;
            if (start.charAt(s) == 'R' && s > e) //from 3
                return false;
            s++;
            e++;
        }
        return true;
    }
}
