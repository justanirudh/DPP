package com.anirudh.interview_prep_2021.spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 4/14/17.
 */
/*
383. Ransom Note
Given an arbitrary ransom note string and another string containing letters from all the magazines,
write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> mag = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            mag.put(c, mag.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (!mag.containsKey(c) || mag.get(c) == 0)
                return false;
            else {
                mag.put(c, mag.get(c) - 1);
            }
        }
        return true;
    }
}
