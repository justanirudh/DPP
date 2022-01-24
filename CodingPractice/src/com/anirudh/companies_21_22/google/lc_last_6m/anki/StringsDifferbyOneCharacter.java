package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1554. Strings Differ by One Character
Medium

160

4

Add to List

Share
Given a list of strings dict where all the strings are of the same length.

Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.



Example 1:

Input: dict = ["abcd","acbd", "aacd"]
Output: true
Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
Example 2:

Input: dict = ["ab","cd","yz"]
Output: false
Example 3:

Input: dict = ["abcd","cccc","abyd","abab"]
Output: true


Constraints:

The number of characters in dict <= 105
dict[i].length == dict[j].length
dict[i] should be unique.
dict[i] contains only lowercase English letters.


Follow up: Could you solve this problem in O(n * m) where n is the length of dict and m is the length of each string.
 */
/*
Option 1:
We calculate hash of each string. Then for each index, we remove the hash of char from teh hash of string and checj
if it in a hashset. 2 words with 1 char diff should have same hash after that diffing char is removed

1. Calculate hash of each string using rolling hash: O(mn)
2. For each index i:
     check from i = len-1 to 0 so that PRIME_NUM can be built on organically
     find hash of the {word - word[i]}
     check if it is the hashset
        If it is, there is a duplicate
Tx: O(mn)
Option 2: compare every string with every other string: Tx: O(n^2 * m)
 */
public class StringsDifferbyOneCharacter {
    public boolean differByOne(String[] dict) {
        int PRIME_NUM = 31;
        long MOD = (long) Math.pow(10, 20) + 7; //10^9 doesnt work
        Map<String, Long> stringToHash = new HashMap<>();
        for (String s : dict) { //calculate hashes
            long hash = 0;
            for (int i = 0; i < s.length(); ++i) {
                hash = hash * PRIME_NUM + (s.charAt(i) - 'a') % MOD;
            }
            stringToHash.put(s, hash);
        }

        long multiple = 1;
        for (int i = dict[0].length() - 1; i >= 0; --i) { //remove ith index and calculate hash
            Set<Long> hashes = new HashSet<>();
            for (String s : dict) {
                long oneDiffHash = stringToHash.get(s) - (multiple * (s.charAt(i) - 'a')) % MOD;
                if (hashes.contains(oneDiffHash))
                    return true;
                hashes.add(oneDiffHash);
            }
            multiple = (multiple * PRIME_NUM) % MOD;
        }
        return false;
    }
}
