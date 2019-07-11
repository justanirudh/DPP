package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 1/10/17.
 */

/*
5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
*/

public class LongestPalindromeSubstring {

    public static String longestPalindrome(String s) {

        //table[i][j] is true if the substring from i to j is a palindrome
        boolean[][] table = new boolean[s.length()][s.length()];

        if (s.length() == 0)
            return "";

        int maxLength;
        int palinStart;
        int palinEnd;

        //length 1
        for (int i = 0; i < s.length(); ++i)
            table[i][i] = true;

        maxLength = 1;
        palinStart = 0;
        palinEnd = 0;

        //length 2
        for (int i = 0; i < s.length() - 1; ++i) {
            int j = i + 1;
            if (s.charAt(i) == s.charAt(j)) {
                table[i][j] = true;
                maxLength = 2;
                palinStart = i;
                palinEnd = j;
            }
        }

        //length 3 and above
        for (int palinLength = 3; palinLength <= s.length(); ++palinLength) {
            //find for each string if palindrome or not
            for (int start = 0; start < s.length() - palinLength + 1; ++start) {
                int end = start + palinLength - 1;
                //check if the the end letters are same && the inner string is palindrome or not
                if (s.charAt(start) == s.charAt(end) && table[start + 1][end - 1]) { //its a palindrome
                    table[start][end] = true;
                    if (palinLength > maxLength) { //it has bigger length
                        maxLength = palinLength;
                        palinStart = start;
                        palinEnd = end;
                    }
                }
            }
        }

        return s.substring(palinStart, palinEnd + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcba"));
    }
}
