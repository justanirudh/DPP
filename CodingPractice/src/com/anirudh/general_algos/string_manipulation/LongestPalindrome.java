package com.anirudh.general_algos.string_manipulation;

import java.util.HashMap;

/**
 * Created by paanir on 4/11/17.
 */
/*
409. Longest Palindrome
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can
be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    /*
    All the even numbers + for every odd number (num - 1) [+ 1, if there is at least 1 odd number]
     */
    public int longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;
        //make map
        for (char c : arr) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        //count
        boolean oneOdd = false; //at least one odd
        for (int curr : map.values()) {
            if (curr % 2 != 0) { //odd
                count += curr - 1;
                if (!oneOdd)
                    oneOdd = true;
            } else { //even
                count += curr;
            }
        }
        if (oneOdd)
            count += 1; //for the biggest odd number, I dont need to drop 1 to make it even, it can come in the center
        return count;
    }
}
