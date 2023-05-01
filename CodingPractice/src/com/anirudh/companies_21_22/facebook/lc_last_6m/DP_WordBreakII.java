package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.*;

/*
 * 140. Word Break II
 * Hard
 * 3688
 * 452
 * Add to List
 * Share
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word
 * is a valid dictionary word. Return all such possible sentences in any order.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 */

/**
 * Simple greedy dynamic programming
 * Make a memoization map of String -> ways to break the string: getWaysToBreak()
 * Use substring(0,i) to find all PREFIXES of the string
 * For each prefix, check if it in the wordSet
 * If it is in wordSet, then send the postFix to getWaysToBreak()
 * With the returned value, add it to memoization map
 * <p>
 * use the memoization map first thing to make sure we do no extra processing
 */
public class DP_WordBreakII {
    Set<String> wordSet;
    Map<String, List<String>> waysToBreakMap = new HashMap<>(); //Map from a string to list of ways it can be broken down

    List<String> getWaysToBreak(String toBeBroken) {

        // toBeBroken.substring(end) from prev call that makes toBeBroken here
        // will return empty string if end is outside the string eg. "abc".substring(3)
        if (toBeBroken.equals("")) {
            return Collections.singletonList("");
        }

        if (waysToBreakMap.containsKey(toBeBroken)) { //if already in the map, return
            return waysToBreakMap.get(toBeBroken);
        } else {
            waysToBreakMap.put(toBeBroken, new ArrayList<>());
        }

        for (int end = 1; end <= toBeBroken.length(); ++end) { //check all substrings
            String prefix = toBeBroken.substring(0, end);

            if (wordSet.contains(prefix)) { //if prefix is in dictionary, then search for postfixes

                String suffix = toBeBroken.substring(end);
                List<String> brokenSuffixes = getWaysToBreak(suffix); //sending rest of the String to recurse

                for (String brokenSuffix : brokenSuffixes) { //add it to map
                    String way = brokenSuffix.isEmpty() ? prefix : prefix + " " + brokenSuffix;
                    waysToBreakMap.get(toBeBroken).add(way);
                }
            }
        }
        return waysToBreakMap.get(toBeBroken);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>(wordDict);

        return getWaysToBreak(s);
    }

}
