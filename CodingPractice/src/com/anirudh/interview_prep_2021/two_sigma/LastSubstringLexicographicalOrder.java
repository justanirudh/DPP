package com.anirudh.interview_prep_2021.two_sigma;

/*
1163. Last Substring in Lexicographical Order
Hard

377

386

Add to List

Share
Given a string s, return the last substring of s in lexicographical order.



Example 1:

Input: s = "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: s = "leetcode"
Output: "tcode"
 */

/*
Option 1:
Brute force: Find all substrings: O(n^2) n = num chars
Find max string in the array: O(n)
Overall complexity = O(n^2)

Option 2: Some shit i dont understand yet

 */
public class LastSubstringLexicographicalOrder {
    public String lastSubstring(String s) {
        int i = 0, j = 1, offset = 0, len = s.length();
        while (i + offset < len && j + offset < len) {
            char c = s.charAt(i + offset), d = s.charAt(j + offset);
            if (c == d) {
                ++offset;
            } else {
                if (c < d) {
                    i += offset + 1;
                } // chars in [i, ..., i + offset] <= charAt(i) == charAt(j)
                else {
                    j += offset + 1;
                } // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
                if (i == j) {
                    ++j;
                } // avoid duplicate start indices.
                offset = 0; // reset offset to 0.
            }
        }
        return s.substring(i);
    }
}
