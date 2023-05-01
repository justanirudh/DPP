package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
140. Word Break II
Hard
6K
504
company
Bloomberg
company
Amazon
company
Apple
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.
 */
public class WordBreakII {
    Set<String> dict;
    Map<String, List<String>> waysToBreak;

    List<String> waysToBreak(String s) {
        // toBeBroken.substring(end) from prev call that makes toBeBroken here
        // will return empty string if end is outside the string eg. "abc".substring(3)
        if (s.isEmpty()) {
            List<String> l = new ArrayList<>();
            l.add(""); //wont return empty so that line 24 runs
            return l;  //cant send empty array because we need to differentiate from failed cases of line 17
        }

        if (waysToBreak.containsKey(s)) {
            return waysToBreak.get(s);
        } else {
            waysToBreak.put(s, new ArrayList<>());
        }

        for (int end = 1; end <= s.length(); ++end) {
            String prefix = s.substring(0, end);
            if (dict.contains(prefix)) {
                List<String> waysToBreakSuffix = waysToBreak(s.substring(end));
                for (String w : waysToBreakSuffix) {
                    String word = w.isEmpty() ? prefix : prefix + " " + w;
                    waysToBreak.get(s).add(word);
                }
            }
        }
        return waysToBreak.get(s);

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        waysToBreak = new HashMap<>();
        return waysToBreak(s);
    }
}
