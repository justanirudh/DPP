package com.anirudh.general_algos;

/**
 * Created by paanir on 7/16/17.
 */
/*
125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty())
            return true;
        String ss = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(char c : ss.toCharArray()){
            if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
                sb.append(c);
        }
        String newSS = sb.toString();
        if(newSS.isEmpty() || newSS.length() == 1)
            return true;
        return newSS.equals(new StringBuilder(newSS).reverse().toString());
    }
}
