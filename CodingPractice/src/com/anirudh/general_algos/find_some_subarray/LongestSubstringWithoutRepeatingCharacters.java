package com.anirudh.general_algos.find_some_subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anirudh on 24/11/16.
 */
/*
3. Longest Substring Without Repeating Characters
Total Accepted: 217620
Total Submissions: 925394
Difficulty: Medium
Contributors: Admin
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3.
Note that the answer must be a substring,  "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    private static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> m = new HashMap<>();
        String currStr = "";
        String maxStr = "";
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(m.containsKey(c)){
                // if in map, remove the part of the current string that ends at the given character
                //then add the given char again to the curr String and do the necessary checking
                int index = currStr.indexOf(c);
                String removed = currStr.substring(0, index + 1); //substring is exclusive of last index
                for(int j = 0; j<removed.length(); ++j) // remove all the respective elements from hashmap
                    m.remove(removed.charAt(j));
                currStr = currStr.substring(index + 1) + c; //take rest of the string and add curr element
            }
            else//if not in map, add to curr string
                currStr += c;
            if(currStr.length() > maxStr.length())
                maxStr = currStr;
            m.put(c, 1);
        }
        System.out.println(maxStr);
        return maxStr.length();
    }

    public static void main(String args[]){
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
