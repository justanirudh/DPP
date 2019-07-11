package com.anirudh.dynamic_programming_greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paanir on 7/14/17.
 */
/*
139. Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be
segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].
 */
//https://www.youtube.com/watch?v=WepWFGxiwRs
public class WordBreak {
    Set<String> dict;
    boolean[][] matrix;
    String s;

    public boolean partsContainedInDict(int start, int end) { //breaking at each index and checking from matrix; memoization here
        for (int i = start; i < end; ++i) {
            int breakpt = i + 1;
            if (matrix[start][breakpt - 1] && matrix[breakpt][end - 1])
                return true;
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        dict = new HashSet<>(wordDict);
        matrix = new boolean[s.length()][s.length()];

        //Example: iamace
        //length 1 words; base case: i,a,m,a,c,e
        for (int i = 0; i < s.length(); ++i) {
            int start = i; //close bracket
            int end = i + 1; //open bracket
            if (dict.contains(s.substring(start, end)))
                matrix[start][end - 1] = true;
        }

        //for 2 length combo (ia, am, ma, ac,ce), 3 length combos (iam, ama, mac, ace)  etc.
        for (int length = 2; length <= s.length(); ++length) {
            int start = 0; //close
            int end = start + length; //open
            while (end <= s.length()) { //equal to because substring's 2nd arg is open bracket
                String sub = s.substring(start, end);
                if (dict.contains(sub))//dict contains the whole substring
                    matrix[start][end - 1] = true;
                else //check if dict contains parts of it
                    matrix[start][end - 1] = partsContainedInDict(start, end);
                start++;
                end = start + length;
            }
        }
        return matrix[0][s.length() - 1];
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("acccbccb", Arrays.asList("cc", "bc", "ac", "ca")));
    }
}

