package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m;

/**
 * Created by paanir on 8/26/21.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 953. Verifying an Alien Dictionary
 * Easy
 * 
 * 2058
 * 
 * 778
 * 
 * Add to List
 * 
 * Share
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * 
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 * 
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 * 
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 * 
 * 
 * Constraints:
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */

/**
 * Basic impl: compare every adjacent pair
 *
 * Catch:  here is to convert the dictionary to a hashmap from character -> {their position in the order}
 * this makes comparing letters O(1) operation
 */
public class VerifyinganAlienDictionary {

    Map<Character, Integer> orderMap;

    boolean compareWords(String word1, String word2) {
        int len = Math.min(word1.length(), word2.length());
        for (int i = 0; i < len; ++i) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 == c2)
                continue;
            return orderMap.get(c1) < orderMap.get(c2);
        }
        return word1.length() < word2.length();
    }

    public boolean isAlienSorted(String[] words, String order) {
        orderMap = new HashMap<>(); //can be an array
        //CATCH
        for (int i = 0; i < 26; ++i) {
            orderMap.put(order.charAt(i), i); //give values to characters
        }
        if (words.length == 0 || words.length == 1 || order.isEmpty())
            return true;
        for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.equals(word2))
                continue;
            boolean sorted = compareWords(word1, word2);
            if (!sorted)
                return false;
        }
        return true;
    }
}
