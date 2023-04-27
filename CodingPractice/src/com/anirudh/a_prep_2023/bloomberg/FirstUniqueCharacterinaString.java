package com.anirudh.a_prep_2023.bloomberg;

import java.util.HashMap;
import java.util.Map;

/*
387. First Unique Character in a String
Easy
7.7K
253
company
Bloomberg
company
Amazon
company
Goldman Sachs
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.



Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1


Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
 */
public class FirstUniqueCharacterinaString {
    class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Integer> count = new HashMap<Character, Integer>();
            int n = s.length();
            // build hash map : character and how often it appears
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                count.put(c, count.getOrDefault(c, 0) + 1);
            }

            // find the index, iterate through string
            for (int i = 0; i < n; i++) {
                if (count.get(s.charAt(i)) == 1)
                    return i;
            }
            return -1;
        }
    }
}
