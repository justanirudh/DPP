package com.anirudh.companies_21_22.google.lc_last_6m;

/*
727. Minimum Window Subsequence
Hard

1133

69

Add to List

Share
Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.

If there is no such window in s1 that covers all characters in s2, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.



Example 1:

Input: s1 = "abcdebdde", s2 = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of s2 in the window must occur in order.
Example 2:

Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
Output: ""


Constraints:

1 <= s1.length <= 2 * 104
1 <= s2.length <= 100
s1 and s2 consist of lowercase English letters.
Accepted
70,636
Submissions
164,889
 */
/*
    fast on s1: to find a correct subsequence, goes from left to right
    slow on s1: to find the shortest subsequence. goes from right to left. goes from last letter of s2 to first letter
    ptr: ptr to traverse s2
 */
public class MinimumWindowSubsequence {
    public String minWindow(String s1, String s2) {
        int fast = 0;
        int minLen = Integer.MAX_VALUE;
        String res = "";
        while (fast < s1.length()) {
            int ptr = 0;
            while (fast < s1.length() && ptr < s2.length()) { //left to right to find a correct subseq
                if (s1.charAt(fast) == s2.charAt(ptr)) {
                    ptr++;
                }
                if (ptr == s2.length()) {
                    break;
                }
                fast++;
            }
            if (ptr != s2.length()) //we didnt get the subsequence
                break; //didnt get any more subseqs

            int slow = fast; //now optimize; go from right to left to find a potentially shorter string
            ptr = s2.length() - 1;
            while (slow >= 0 && ptr >= 0) {
                if (s1.charAt(slow) == s2.charAt(ptr)) {
                    ptr--;
                }
                if (ptr == -1) {
                    break;
                }
                slow--;
            }
            int len = fast - slow + 1;
            if (len < minLen) {
                minLen = len;
                res = s1.substring(slow, fast + 1);
            }
            fast = slow + 1; //start finding from the letter next to start of previous subseq
        }
        return res;
    }
}
