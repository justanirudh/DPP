package com.anirudh.subarray_substring.string_manipulation;

/**
 * Created by paanir on 1/17/17.
 */
/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

 */
//beats 8%. Trie beats 23%
public class LongestCommonPrefix {


    /*
    T = O(n^2)
    S = O(1)
     */
    private boolean isAllSame(char curr, int i, String[] strs) {
        for (String str : strs) {
            if (str.charAt(i) != curr)
                return false;
        }
        return true;
    }

    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        if (strs == null || strs.length == 0)
            return "";
        for (String str : strs) {
            int len = str.length();
            if (len == 0)
                return "";
            minLen = Math.min(len, minLen);
        }
        StringBuilder sb = new StringBuilder();
        String first = strs[0];
        for (int i = 0; i < minLen; ++i) {
            //if all same
            char curr = first.charAt(i);
            if (isAllSame(curr, i, strs))
                sb.append(curr);
            else
                break;
        }
        return sb.toString();
    }

    public String longestCommonPrefixDifferentWay(String[] strs) {
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
