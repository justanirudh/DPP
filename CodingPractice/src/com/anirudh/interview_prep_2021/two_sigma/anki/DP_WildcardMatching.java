package com.anirudh.interview_prep_2021.two_sigma.anki;

/*
44. Wildcard Matching
Hard

3624

162

Add to List

Share
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input: s = "acdcb", p = "a*c?b"
Output: false
 */

/*

dp[p][s] = whether pattern until pth index matches the string until sth index
1st all base cases:
1. p equals s; p has all *; p and s both empty
2. Create a 2D array: rows are pattern, columns are letters of string
3. start iterating row-wise i.e. through pattern array. At each row in pattern, populate all string columns
4. if it is NOT * OR ?: For each column in the row: d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] && (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
5. If it is ?: For each column in the row: d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
6. If it is * then
6.1 iterate through row until you see d[pIdx-1][sIdx-1] is true;
6.2 then d[pIdx][sIdx - 1] to end of row is all true
7. at the end, return d[pLen][sLen]

Write a function to autogenerate test cases.
Fix a length. Or randomize that too
Option 1: 26 letters, 1 *, 1 ? | rand in the array | prob low of getting * and ?
Option 2: 26 letters, 13 *, 13 ? | 2: 1: 1 prob of each
Option 3:
3.1 RandomPickWithWeight: 1:1:1:....1:13:13 weights
3.2 create runningSum array: 1,2,3,...26, 39, 52
3.2 pick rand between 1 and 52. Do binary search in array to find where it falls

 */
public class DP_WildcardMatching {

    public boolean isMatch(String s, String p) {

        if(p.equals(s))
            return true;

        if (s.isEmpty() && p.isEmpty())
            return true;
        if (!s.isEmpty() && p.isEmpty())
            return false;

        //check if p is all **
        boolean isPatternAllAsterix = true;
        for (char c : p.toCharArray()) {
            if (c != '*') {
                isPatternAllAsterix = false;
                break;
            }
        }
        if (isPatternAllAsterix)
            return true;

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1]; //initiates all as false
        dp[0][0] = true;

        for (int pIdx = 1; pIdx < p.length() + 1; ++pIdx) { //go row-wise
            if (p.charAt(pIdx - 1) != '*' && p.charAt(pIdx - 1) != '?') { //alphabet

                for (int sIdx = 1; sIdx < s.length() + 1; ++sIdx) { //true until now && true right now
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1] && p.charAt(pIdx - 1) == s.charAt(sIdx - 1);
                }

            } else if (p.charAt(pIdx - 1) == '?') {

                for (int sIdx = 1; sIdx < s.length() + 1; ++sIdx) { //true until now (? matches with any char in string)
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
                }

            } else { //'*'
                int sIdx = 1;

                while ((!dp[pIdx - 1][sIdx - 1]) && (sIdx < s.length() + 1)) { // Find the first idx in string with the previous match.
                    sIdx++;
                }

                //whatever the status of the previous row is, take it; might be false or true
                //If the bottom row is false; * row will all be false as well
                dp[pIdx][sIdx - 1] = dp[pIdx - 1][sIdx - 1];

                while (sIdx < s.length() + 1) { //rest of the chars will be all true as * matches to the rest of the string
                    dp[pIdx][sIdx] = true;
                    sIdx++;
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
