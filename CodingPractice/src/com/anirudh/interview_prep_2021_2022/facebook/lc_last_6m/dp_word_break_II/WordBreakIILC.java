package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m.dp_word_break_II;

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
 *
 * use the memoization map first thing to make sure we do no extra processing
 */
public class WordBreakIILC {
    Set<String> wordSet;
    Map<String, List<List<String>>> breakMap = new HashMap<>(); //Map from a string to list of ways it can be broken down

    List<List<String>> getWaysToBreak(String toBeBroken) {

        if (toBeBroken.equals("")) { //base case
            List<List<String>> solutions = new ArrayList<>();
            solutions.add(new ArrayList<>());
            return solutions;
        }

        if (breakMap.containsKey(toBeBroken)) { //if already in the map, return
            return breakMap.get(toBeBroken);
        } else {
            breakMap.put(toBeBroken, new ArrayList<>());
        }

        for (int i = 1; i <= toBeBroken.length(); ++i) { //check all substrings
            String prefix = toBeBroken.substring(0, i);

            if (wordSet.contains(prefix)) { //if prefix is in dictionary, then search for postfixes

                String postFix = toBeBroken.substring(i);
                List<List<String>> brokenPostfixes = getWaysToBreak(postFix); //sending rest of the String to recurse

                for (List<String> brokenPostfix : brokenPostfixes) { //add it to map
                    List<String> wordBreak = new ArrayList<>(brokenPostfix);
                    wordBreak.add(0, prefix); //inserting in right order so that joining is easy
                    breakMap.get(toBeBroken).add(wordBreak); //adding to breakMap for memoization
                }
            }
        }
        return breakMap.get(toBeBroken);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>(wordDict);

        List<List<String>> waysToBreak = getWaysToBreak(s);

        // make sentences from broken words and return
        List<String> res = new ArrayList<>();
        for (List<String> words : waysToBreak) {
            StringJoiner sj = new StringJoiner(" ");
            for(String word : words) {
                sj.add(word);
            }
            res.add(sj.toString());
        }

        return res;
    }

}
