package com.anirudh.general_algos;

import java.util.Arrays;

/**
 * Created by paanir on 4/13/17.
 */
/*
389. Find the Difference
Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a
random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.
 */
public class FindtheDifference {
    public char findTheDifference(String s, String t) {
        char[] char_s = s.toCharArray();
        Arrays.sort(char_s);

        char[] char_t = t.toCharArray();
        Arrays.sort(char_t);

        for (int i = 0; i < s.length(); ++i) {
            if (char_s[i] != char_t[i])
                return char_t[i];
        }
        return char_t[s.length()];
    }
}
