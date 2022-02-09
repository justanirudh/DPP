package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.HashMap;
import java.util.Map;

/*
266. Palindrome Permutation
Easy

824

63

Add to List

Share
Given a string s, return true if a permutation of the string could form a palindrome.



Example 1:

Input: s = "code"
Output: false
Example 2:

Input: s = "aab"
Output: true
Example 3:

Input: s = "carerac"
Output: true


Constraints:

1 <= s.length <= 5000
s consists of only lowercase English letters.
Accepted
154,281
Submissions
237,827
 */
/*
    if even number of elems, each even
    if odd num of elems, only 1 odd, rest even
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        int len = s.length();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        if (len % 2 == 0) { //all even
            for (Integer val : freq.values()) {
                if (val % 2 != 0) {
                    return false;
                }
            }
            return true;
        } else { //atmost 1 odd
            int count = 0;
            for (Integer val : freq.values()) {
                if (val % 2 != 0) {
                    if (count == 0) {
                        count++;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
