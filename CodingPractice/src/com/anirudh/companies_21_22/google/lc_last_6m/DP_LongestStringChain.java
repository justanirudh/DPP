package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;

/**
 * Created by paanir on 9/3/21.
 */
/*
1048. Longest String Chain
Medium

2394

121

Add to List

Share
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.



Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 */

/*
Similar to WordBreakII in Facebook
Top down DP: for each string find longest chain ENDING in that string
Memoization: Use String -> length of longest chain ending at the string
For each word, delete each char, do recursion, add the char back and delete next char

Tx: O(N * n^2) where N is total words and n is char per word
For each word (N)
    we are making n words
        making 1 word = O(n) {shift letters}
        making n words : O(n^2)
 */
public class DP_LongestStringChain {

    Map<String, Integer> maxLenMap = new HashMap<>();
    Set<String> wordSet = new HashSet<>();

    int maxChainLengthEndingAt(String word) {
        if (maxLenMap.containsKey(word))
            return maxLenMap.get(word);

        int maxLen = 1; //just the current word

        StringBuilder wordSB = new StringBuilder(word);

        for (int i = 0; i < wordSB.length(); ++i) {
            char c = wordSB.charAt(i);
            wordSB.deleteCharAt(i);
            if (wordSet.contains(wordSB.toString())) {
                //do recursion
                int chainLen = 1 + maxChainLengthEndingAt(wordSB.toString());
                maxLen = Math.max(maxLen, chainLen);
            }
            wordSB.insert(i, c); //add it back for next deletion
        }
        maxLenMap.put(word, maxLen); //PUT in the MAP
        return maxLen;
    }

    public int longestStrChain(String[] words) {
        int maxLen = 0;

        wordSet.addAll(Arrays.asList(words));
        for (String word : words) {
            int len = maxChainLengthEndingAt(word);
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
