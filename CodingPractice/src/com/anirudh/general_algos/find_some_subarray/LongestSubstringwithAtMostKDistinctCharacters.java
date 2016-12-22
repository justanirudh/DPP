package com.anirudh.general_algos.find_some_subarray;

import java.util.HashMap;

/**
 * Created by paanir on 12/20/16.
 */
/*
This is a problem asked by Google.

Given a string, find the longest substring that contains only two unique characters. For example, given "abcbbbbcccbdddadacb",
the longest substring that contains 2 unique character is "bcbbbbcccb" (length 10).
 */
public class LongestSubstringwithAtMostKDistinctCharacters {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s == null || s.length() == 0)
            return 0;

        if (s.length() < k)
            return s.length();

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        int maxLen = k;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {

            //adding to map
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }

            //if size of map > provided k
            if (map.size() > k) {
                maxLen = Math.max(maxLen, i - left);

                while (map.size() > k) {

                    //remove elements from left until size of map is equal to k
                    char fc = s.charAt(left);
                    if (map.get(fc) == 1) {
                        map.remove(fc);
                    } else {
                        map.put(fc, map.get(fc) - 1);
                    }
                    //increment left pointer
                    left++;
                }
            }

        }

        maxLen = Math.max(maxLen, s.length() - left);

        return maxLen;
    }

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstringKDistinct(  "abcbbbbcccbdddadacb", 2 ));
    }
}
