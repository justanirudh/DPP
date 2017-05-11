package com.anirudh.general_algos;

import java.util.HashMap;

/**
 * Created by paanir on 1/22/17.
 */

/*

290. Word Pattern   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 64301
Total Submissions: 200227
Difficulty: Easy
Contributors: Admin
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        //make 2 hashmaps: char to word and word to char
        HashMap<Character, String> patToWordmap = new HashMap<>();
        HashMap<String, Character> wordToPatmap = new HashMap<>();

        String[] wordArr = str.split(" ");

        if (pattern.length() != wordArr.length)
            return false;
        for (int i = 0; i < pattern.length(); ++i) {
            char currChar = pattern.charAt(i);
            String currWord = wordArr[i];
            if (!patToWordmap.containsKey(currChar) && !wordToPatmap.containsKey(currWord)) {
                patToWordmap.put(currChar, currWord);
                wordToPatmap.put(currWord, currChar);
            } else if (patToWordmap.containsKey(currChar) && !wordToPatmap.containsKey(currWord))
                return false;
            else if (!patToWordmap.containsKey(currChar) && wordToPatmap.containsKey(currWord))
                return false;
            else { //both maps have it, now check if the values same
                if (!patToWordmap.get(currChar).equals(currWord))
                    return false;
                if (!wordToPatmap.get(currWord).equals(currChar))
                    return false;
            }
        }
        return true;
    }
}
