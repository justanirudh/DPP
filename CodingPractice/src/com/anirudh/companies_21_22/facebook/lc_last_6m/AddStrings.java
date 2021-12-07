package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/29/21.
 */

/**
 * 415. Add Strings
 Easy

 2262

 439

 Add to List

 Share
 Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

 You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

 Example 1:

 Input: num1 = "11", num2 = "123"
 Output: "134"
 Example 2:

 Input: num1 = "456", num2 = "77"
 Output: "533"
 Example 3:

 Input: num1 = "0", num2 = "0"
 Output: "0"
 */

/**
 * Do summation using textbook addition, one digit at a time
 * T : O (Max (num1, num2)), S: : O (Max (num1, num2))
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        String num1r = new StringBuilder(num1).reverse().toString();
        String num2r = new StringBuilder(num2).reverse().toString();
        int maxLen = Math.max(num1.length(), num2.length());
        int carry = 0;
        for(int i = 0; i < maxLen; ++i) {
            int d1 = i < num1r.length() ? num1r.charAt(i) - '0' : 0;
            int d2 = i < num2r.length() ? num2r.charAt(i) - '0' : 0;
            int sum = d1 + d2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry == 1)
            sb.append(1);
        return sb.reverse().toString();
    }

}
