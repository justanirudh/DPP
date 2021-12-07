package com.anirudh.companies_21_22.spotify;

import java.util.HashSet;
import java.util.Set;

/*
3. Longest Substring Without Repeating Characters
Medium

17292

817

Add to List

Share
Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 */

/*
Kind of like sliding window
Have 2 pointers: fast and slow; Maintain a hashset of chars as you iterate
If fast sees a char already in the set, increment slow until the char is out of the slow<> fast window
keep a maxLen = Math.max(maxLen, size of set)
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty())
            return 0;

        int slow = 0;
        int maxLen = Integer.MIN_VALUE;
        Set<Character> window = new HashSet<>();
        for (int fast = 0; fast < s.length(); fast++) {
            char curr = s.charAt(fast);
            if (!window.contains(curr)) {
                window.add(curr);
                maxLen = Math.max(maxLen, window.size());
                continue;
            }
            //present contains curr already
            while (s.charAt(slow) != curr) {
                window.remove(s.charAt(slow));
                slow++;
            }
            slow++; // no need to remove s.charAt(slow) as it is same as curr which we are anyway going to add
            //not comparing window size again as we have only removed elems
        }
        return maxLen;
    }
}
