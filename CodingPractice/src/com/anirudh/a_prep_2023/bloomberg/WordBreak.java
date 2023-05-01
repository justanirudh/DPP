package com.anirudh.a_prep_2023.bloomberg;
import java.util.*;
/*
139. Word Break
Medium
13.9K
583
company
Bloomberg
company
Amazon
company
Facebook
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 */
public class WordBreak {
    Map<Integer, Boolean> canBeBrokenFromHere = new HashMap<>();
    Set<String> dict;


    private boolean canBeBroken(String s, int start) {
        if (start == s.length()) {
            return true;
        }

        if (canBeBrokenFromHere.containsKey(start))
            return canBeBrokenFromHere.get(start);

        for (int end = start + 1; end <= s.length(); ++end) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix) && canBeBroken(s, end)) {
                canBeBrokenFromHere.put(start, true); //from this start to end of original string, we can break
                return true;
            }
        }
        canBeBrokenFromHere.put(start, false);
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        return canBeBroken(s, 0);

    }
}
