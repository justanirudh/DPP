package com.anirudh.general_algos;

/**
 * Created by paanir on 3/31/17.
 */
/*
9. Palindrome Number
Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        char[] charArr = Integer.toString(x).toCharArray();
        int s = 0;
        int e = charArr.length - 1;
        while (s < e) {
            if (charArr[s] != charArr[e])
                return false;
            s++;
            e--;
        }
        return true;
    }
}
