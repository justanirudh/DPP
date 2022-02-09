package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

import java.util.*;

/*
616. Add Bold Tag in String
Medium

857

139

Add to List

Share
You are given a string s and an array of strings words. You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words. If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag. If two substrings wrapped by bold tags are consecutive, you should combine them.

Return s after adding the bold tags.



Example 1:

Input: s = "abcxyz123", words = ["abc","123"]
Output: "<b>abc</b>xyz<b>123</b>"
Example 2:

Input: s = "aaabbcc", words = ["aaa","aab","bc"]
Output: "<b>aaabbc</b>c"


Constraints:

1 <= s.length <= 1000
0 <= words.length <= 100
1 <= words[i].length <= 1000
s and words[i] consist of English letters and digits.
All the values of words are unique.


Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
/*
1. find (start, end) intervals of each substring in s
    Use s.indexOf(sub, start) to find each interval
2. sort all the (start,end) by start
3. Find overlaps of these intervals
4. Now add bold tags to each overlap
    for each overlap,
        look at end of previous overlap
        add substring from (end of previous) to (start of new)
        add <b>overlap</b>

Tx: O(T): O(nlogn)
Sx: O(n)
 */
public class AddBoldTaginString {

    public String addBoldTag(String s, String[] words) {
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return s;
        }
        List<List<Integer>> intervals = new ArrayList<>();
        for (String sub : words) {
            int start = 0;
            while (s.indexOf(sub, start) != -1) {
                int first = s.indexOf(sub, start);
                int last = first + sub.length() - 1;
                List<Integer> points = new ArrayList<>();
                points.add(first);
                points.add(last);
                intervals.add(points);
                start = first + 1; //from next letter
            }
        }

        if (intervals.isEmpty())
            return s;

        //all intervals
        intervals.sort(Comparator.comparingInt(x -> x.get(0))); //sort by starting times

        Deque<List<Integer>> overlaps = new ArrayDeque<>();
        //find overlapping intervals
        int start = intervals.get(0).get(0); //first starting point
        int end = intervals.get(0).get(1); //first end point
        for (List<Integer> interval : intervals) {
            if (interval.get(0) <= end || interval.get(0) == end + 1) {
                end = Math.max(end, interval.get(1));
            } else {
                overlaps.offer(Arrays.asList(start, end));
                start = interval.get(0); //next starting point
                end = interval.get(1); //next end point
            }
        }
        overlaps.offer(Arrays.asList(start, end)); //to close last one

        //add bold tags
        StringBuilder sb = new StringBuilder();
        List<Integer> prev = null;
        while (!overlaps.isEmpty()) {
            List<Integer> interval = overlaps.poll();
            start = interval.get(0);
            end = interval.get(1);
            if (prev != null) { //prepend
                sb.append(s, prev.get(1) + 1, start);
            } else if (start > 0) { //prepend
                sb.append(s, 0, start);
            }
            sb.append("<b>");
            sb.append(s, start, end + 1);
            sb.append("</b>");
            prev = interval;
        }
        //add last
        sb.append(s.substring(prev.get(1) + 1));
        return sb.toString();
    }
}
