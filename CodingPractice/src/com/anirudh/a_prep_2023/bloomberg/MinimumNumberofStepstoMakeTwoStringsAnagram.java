package com.anirudh.a_prep_2023.bloomberg;

import java.util.*;

/*
1347. Minimum Number of Steps to Make Two Strings Anagram
Medium
1.8K
77
company
Bloomberg
company
DoorDash
IXL
You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.



Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
Example 2:

Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
Example 3:

Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams.


Constraints:

1 <= s.length <= 5 * 104
s.length == t.length
s and t consist of lowercase English letters only.
 */

/*
Make freq map of first string
subtract 2n string from 1st
get sum of remaining freqs

Sx: O(1) (O(26))
Tx: O(n)

 */
public class MinimumNumberofStepstoMakeTwoStringsAnagram {
    public int minSteps(String s, String t) {
        Map<Character, Integer> first = new HashMap<>();
        for (char c : s.toCharArray()) {
            first.put(c, first.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (first.containsKey(c)) {
                first.put(c, first.get(c) - 1);
                if (first.get(c) == 0)
                    first.remove(c);
            }
        }
        int res = 0;
        for (int val : first.values()) {
            res += val;
        }
        return res;
    }
}
