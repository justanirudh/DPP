package com.anirudh;

import java.util.Arrays;

/**
 * Created by paanir on 1/14/17.
 */
/*
242. Valid Anagram   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 131148
Total Submissions: 292188
Difficulty: Easy
Contributors: Admin
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        char[] schars = s.toCharArray();
        Arrays.sort(schars);
        String sNew = new String(schars);

        char[] tchars = t.toCharArray();
        Arrays.sort(tchars);
        String tNew = new String(tchars);

        return sNew.equals(tNew);
    }
}
