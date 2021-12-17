package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.HashMap;
import java.util.Map;

/*
833. Find And Replace in String
Medium

636

671

Add to List

Share
You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.

To complete the ith replacement operation:

Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
Otherwise if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.

For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.



Example 1:


Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
Example 2:


Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation:
"ab" occurs at index 0 in s, so we replace it with "eee".
"ec" does not occur at index 2 in s, so we do nothing.
 */
/*
1. Go over indices array, if string.substring(index[i]).startsWith(sources[i]) = target[i]
create map of index  -> {num_letters_to_be_replaced, target string}
2. Go over original string, while creating new string; if index is not in map, copy from orig string
if index is in map, copy from map's target string and increment index on original string by num_letters_to_be_replaced
 */
public class FindAndReplaceinString {

    static class Replace {
        int numLetters;
        String target;
        Replace(int numLetters, String target) {
            this.numLetters = numLetters;
            this.target = target;
        }
    }
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Replace> replacements = new HashMap<>();
        for(int i = 0; i < indices.length; ++i) {
            int strIdx = indices[i];
            if(s.substring(strIdx).startsWith(sources[i])) {
                replacements.put(strIdx, new Replace(sources[i].length(), targets[i]));
            }
        }
        StringBuilder sb = new StringBuilder();
        int strIdx = 0;
        while(strIdx <= s.length() - 1) {
            if(!replacements.containsKey(strIdx)) {
                sb.append(s.charAt(strIdx));
                strIdx++;
            }
            else { //in map
                sb.append(replacements.get(strIdx).target);
                strIdx += replacements.get(strIdx).numLetters;
            }
        }
        return sb.toString();
    }
}
