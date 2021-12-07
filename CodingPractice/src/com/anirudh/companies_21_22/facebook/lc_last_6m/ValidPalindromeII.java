package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/26/21.
 */

/**
 * 680. Valid Palindrome II
 * Easy
 * <p>
 * 3098
 * <p>
 * 191
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string number, return true if the number can be palindrome after deleting at most one character from it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: number = "aba"
 * Output: true
 * Example 2:
 * <p>
 * Input: number = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 * <p>
 * Input: number = "abc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= number.length <= 105
 * number consists of lowercase English letters.
 */

/**
 * go from either side and check until both same
 * after finding first difference, check by removing either left side letter or right side letter
 * and then check if the rest of the substring is still a palindrome or not
  */
public class ValidPalindromeII {

    boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1)
            return true;
        for (int i = 0; i < s.length(); ++i) {
            int end = s.length() - 1 - i;
            if (i < end) {
                if (s.charAt(i) != s.charAt(end))
                    return false;
            } else
                break;
        }
        return true;
    }


    public boolean validPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1)
            return true;

        for (int i = 0; i < s.length(); ++i) {
            int end = s.length() - 1 - i;
            if (i < end) {
                if (s.charAt(i) != s.charAt(end)) {
                    //remove from either side and check
                    boolean left = isPalindrome(s.substring(i, end));
                    if (left)
                        return true;
                    return isPalindrome(s.substring(i + 1, end + 1));
                }
            } else
                break;
        }
        //hasnt returned yet means is a palindrome already
        return true;
    }
}
