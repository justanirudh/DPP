package com.anirudh.general_algos.fluff;

import java.util.*;

/**
 * Created by paanir on 4/29/17.
 */
/*
500. Keyboard Row
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {
    Set<Character> hs1 = new HashSet<Character>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
    Set<Character> hs2 = new HashSet<Character>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
    Set<Character> hs3 = new HashSet<Character>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));

    public boolean inARow(String s) {
        char[] chars = s.toCharArray();

        List<Set<Character>> sets = new ArrayList<>();
        sets.add(hs1);
        sets.add(hs2);
        sets.add(hs3);

        for (Set<Character> hs : sets) {
            boolean found = true;
            for (Character c : chars) {
                if (!hs.contains(Character.toLowerCase(c))) {
                    found = false;
                    break;
                }
            }
            if (found)
                return true;
        }
        return false;
    }

    public String[] findWords(String[] words) {
        List<String> row = new ArrayList<String>();
        for (String w : words) {
            if (inARow(w))
                row.add(w);
        }
        return row.toArray(new String[row.size()]);
    }
}
