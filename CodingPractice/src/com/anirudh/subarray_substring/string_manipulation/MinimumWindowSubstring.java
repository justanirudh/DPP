package com.anirudh.subarray_substring.string_manipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 12/23/16.
 */
/*
*
76. Minimum Window Substring   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 83405
Total Submissions: 353223
Difficulty: Hard
Contributors: Admin
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

//Sliding window
    //Use same map as used in 340. Longest Substring with At Most K Distinct Characters to track frequency of chars as well
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        String longStr = s;
        String shortStr = t;
        if (t.length() > s.length())
            return "";

        String result = "";

        //character counter for t
        Map<Character, Integer> tmap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (tmap.containsKey(c)) {
                tmap.put(c, tmap.get(c) + 1);
            } else {
                tmap.put(c, 1);
            }
        }

        // character counter for s
        Map<Character, Integer> smap = new HashMap<>();
        int left = 0;
        int minLen = s.length() + 1;

        int count = 0; // the total of mapped characters

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (tmap.containsKey(curr)) { //increasing count until smap same as tmap
                if (smap.containsKey(curr)) {
                    if (smap.get(curr) < tmap.get(curr)) {
                        count++;
                    }
                    smap.put(curr, smap.get(curr) + 1);
                } else {
                    smap.put(curr, 1);
                    count++;
                }
            }

            if (count == t.length()) {
                char scurr = s.charAt(left);
                //increment left
                //stop when curr is part of smap and its value in smap <= value in tmap
                while (!smap.containsKey(scurr) || smap.get(scurr) > tmap.get(scurr)) {
                    if (smap.containsKey(scurr) && smap.get(scurr) > tmap.get(scurr))
                        smap.put(scurr, smap.get(scurr) - 1);
                    left++;
                    scurr = s.charAt(left);
                }

                if (i - left + 1 < minLen) { //update min length if endIndex - startIndex + 1 is less than minLength
                    result = s.substring(left, i + 1);
                    minLen = i - left + 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
