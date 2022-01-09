package com.anirudh.companies_21_22.google.lc_last_6m;

/*
792. Number of Matching Subsequences
Medium

2397

129

Add to List

Share
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
For all words: Make a map of first letter -> all words starting with that letter
Iterate through master-string ONCE, while doing that move around values in maps as we fulfill subsequences
{letter} -> {All words waiting for that letter}
 */
public class NumberofMatchingSubsequences {

    static class Subseq {
        String word;
        int index; //"word" is waiting for letter at this "index" (Or we can keep creating substrings which is a bit costly)
        Subseq(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<Subseq>> map = new HashMap<>();

        for(char c = 'a'; c <= 'z'; ++c) { //initialize map
            map.put(c, new ArrayList<>());
        }

        for(String word : words) {
            char startingChar = word.charAt(0);
            map.get(startingChar).add(new Subseq(word, 0));
        }

        int res = 0;
        for(char c : s.toCharArray()) {
            List<Subseq> waiting = map.get(c);
            map.put(c, new ArrayList<>()); //reset list first, so that if there are repeating letters, they are added back
            for(Subseq sq : waiting) {
                sq.index++; //increment index
                if(sq.index == sq.word.length()) { //exhausted all letters in word
                    res++;
                }
                else {
                    char newStartingChar = sq.word.charAt(sq.index);
                    map.get(newStartingChar).add(new Subseq(sq.word, sq.index)); //move to new place
                }
            }
        }
        return res;
    }

 //-----------------------------------   //    //Gives TLE
    boolean isSubsequence(String string, String sub) {
        int subI = 0;
        for(int i = 0; i < string.length(); ++i) {
            if(subI == sub.length()) {
                return true;
            }
            if(string.charAt(i) == sub.charAt(subI)) {
                subI++;
            }
        }
        return subI == sub.length();
    }

    public int numMatchingSubseqSlow(String s, String[] words) {
        int count = 0;
        for(String word : words) {
            if(isSubsequence(s, word))
                count++;
        }
        return count;
    }
}

