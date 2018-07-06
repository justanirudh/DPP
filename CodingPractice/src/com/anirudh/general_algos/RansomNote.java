package com.anirudh.general_algos;

import java.util.HashMap;

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
        HashMap<Character, Integer> rN = new HashMap<>();
        for(char c : ransomNote.toCharArray()){
            if(rN.containsKey(c))
                rN.put(c, rN.get(c) + 1);
            else
                rN.put(c, 1);
        }
        HashMap<Character, Integer> m = new HashMap<>();
        for(char c : magazine.toCharArray()){
            if(m.containsKey(c))
                m.put(c, m.get(c) + 1);
            else
                m.put(c, 1);
        }
        for(char c: rN.keySet()){
            if(!m.containsKey(c) || m.get(c) < rN.get(c))
                return false;
        }
        return true;
    }
}
