package com.anirudh.interview_prep_2021.two_sigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
49. Group Anagrams
Medium

6820

255

Add to List

Share
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]
 */

/*
Create a Map of { Character count map -> list of strings  }
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character, Integer>, List<String>> dictToStrings = new HashMap<>();
        for (String str : strs) {
            Map<Character, Integer> countMap = new HashMap<>();
            for (char c : str.toCharArray()) {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
            if (!dictToStrings.containsKey(countMap)) {
                List<String> stringList = new ArrayList<>();
                stringList.add(str);
                dictToStrings.put(countMap, stringList);
            } else {
                dictToStrings.get(countMap).add(str);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> value : dictToStrings.values()) {
            res.add(value);
        }
        return res;
    }
}
