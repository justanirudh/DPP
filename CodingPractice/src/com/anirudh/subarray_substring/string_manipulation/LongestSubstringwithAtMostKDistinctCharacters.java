package com.anirudh.subarray_substring.string_manipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 12/20/16.
 */
/*
340. Longest Substring with At Most K Distinct Characters
Hard

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.



This is a problem asked by Google.

Given a string, find the longest substring that contains only two unique characters. For example, given "abcbbbbcccbdddadacb",
the longest substring that contains 2 unique character is "bcbbbbcccb" (length 10).
 */

//Sliding window
public class LongestSubstringwithAtMostKDistinctCharacters {

    public void addOrIncrement(Map<Character, Integer> map, Character c) {
        if (!map.containsKey(c))
            map.put(c, 0);
        map.put(c, map.get(c) + 1);
    }

    public void deleteOrDecrement(Map<Character, Integer> map, Character c) {
        if (map.get(c) > 1)
            map.put(c, map.get(c) - 1);
        else
            map.remove(c);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s == null || s.length() == 0)
            return 0;

        if (s.length() < k)
            return s.length();

        Map<Character, Integer> map = new HashMap<>();

        int maxLen = k;
        int right = 0, left = 0;
        for (; right < s.length(); right++) {
            //Adding frequency of character to map
            addOrIncrement(map, s.charAt(right));

            //Keep adding as long as map size is less than k i.e. we are still within K distinct elems limit
            //if more distinct elements in map total than k, start removing from left
            if (map.size() > k) {
                maxLen = Math.max(maxLen, right - left); //we are not adding the just added element, hence not right - left + 1
                /*
                    remove elements from left until size of map is equal to k
                    map.size() gives the number of distinct elems
                    if they are more than desired, start removing them
                 */
                while (map.size() > k) {
                    deleteOrDecrement(map, s.charAt(left));
                    left++; //increment left pointer
                }
            }
        }

        maxLen = Math.max(maxLen, right - left); //last comparison

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringwithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct("abcbbbbcccbdddadacb", 2));
    }
}
