package com.anirudh.subarray_substring.string_manipulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

//similar to longest substring with atmost k distinct characters

//Sliding window
public class LongestSubstringWithoutRepeatingCharacters {

    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int left = 0, right = 0;
        int max = 0;

        Set<Character> nonRepeating = new HashSet<>();

        while (right < s.length()) {
            char currChar = s.charAt(right);
            if (!nonRepeating.contains(currChar)) {
                nonRepeating.add(currChar);
            } else { //set already has char which is at right
                //keep incrementing left until char at right is removed from set
                while (s.charAt(left) != s.charAt(right)) {
                    nonRepeating.remove(s.charAt(left));
                    left++;
                }
                left++;//remove the repeated char
            }
            max = Math.max(max, right - left + 1);
            right++; //continue
        }
        return max;
    }

    public static void main(String args[]) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}




