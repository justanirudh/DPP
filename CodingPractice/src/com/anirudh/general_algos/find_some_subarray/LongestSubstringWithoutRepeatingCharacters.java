package com.anirudh.general_algos.find_some_subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anirudh on 24/11/16.
 */
/*
3. Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3.
Note that the answer must be a substring,  "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    private static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int startIndex = 0;
        for (int endIndex = 0; endIndex < s.length(); ++endIndex) {
            if (map.containsKey(s.charAt(endIndex))) {
                startIndex = Math.max(startIndex, map.get(s.charAt(endIndex)) + 1); //Math.max to ignore if startIndex more than lastOccur + 1 (abcebafgh, 2nd a)
            }
            map.put(s.charAt(endIndex), endIndex);
            max = Math.max(max, endIndex - startIndex + 1);
        }
        return max;
    }


    public static void main(String args[]) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
