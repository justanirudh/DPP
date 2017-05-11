package com.anirudh.general_algos;

import java.util.HashMap;

/**
 * Created by paanir on 3/10/17.
 */
/*
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for (int i = 0; i < sArr.length; ++i) {
            char cs = sArr[i];
            char ct = tArr[i];
            if (map.containsKey(cs)) {
                //check if value equal to key or not. If it isnt, return false
                if (map.get(cs) != ct)
                    return false;
            } else {
                //check if value is present or not. If it is, return false
                if (map.values().contains(ct))
                    return false;
                map.put(cs, ct);
            }
        }
        return true;
    }

}
