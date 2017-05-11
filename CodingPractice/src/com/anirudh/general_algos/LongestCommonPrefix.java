package com.anirudh.general_algos;

/**
 * Created by paanir on 1/17/17.
 */
/*
14. Longest Common Prefix   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 145283
Total Submissions: 474757
Difficulty: Easy
Contributors: Admin

Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String masterString = strs[0];
        for (int i = 1; i < strs.length; ++i) { //comparing 1 with 2, finding out common (com), then comparing com with 3, changing com, then com with 4....
            StringBuilder sb = new StringBuilder();
            String curr = strs[i];
            int shorterStrLen = Math.min(masterString.length(), curr.length());
            for (int j = 0; j < shorterStrLen; ++j) {
                if (masterString.charAt(j) == curr.charAt(j)) {
                    sb.append(masterString.charAt(j));
                } else
                    break;
            }
            masterString = sb.toString();
        }
        return masterString;
    }
}
