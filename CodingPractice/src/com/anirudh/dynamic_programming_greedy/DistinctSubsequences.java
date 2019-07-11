package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 7/11/17.
 */
/*

115. Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of
the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of
"ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        //t is a subsequence of s
        int[][] matrix = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; ++j)
            matrix[0][j] = 1;
        /*
        if(s[i] == t[j])
            // value with t[i] and s[j] not taken + previous value
            matrix[i][j] = matrix[i-1][j-1] + matrix[i][j-1];
        else
            //if not equal, just thake the previous one
            matrix[i][j] = matrix[i][j-1]
        */
        for (int i = 1; i < t.length() + 1; ++i) {
            for (int j = 1; j < s.length() + 1; ++j) {
                if (t.charAt(i - 1) == s.charAt(j - 1))
                    matrix[i][j] = matrix[i - 1][j - 1] + matrix[i][j - 1];
                else
                    matrix[i][j] = matrix[i][j - 1];
            }
        }
        return matrix[t.length()][s.length()];
    }
}
