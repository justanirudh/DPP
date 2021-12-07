package com.anirudh.companies_21_22.two_sigma;

/*
43. Multiply Strings
Medium

3055

1204

Add to List

Share
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.



Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
 */


/*
1. Create array of (len1 + len2 - 1) size
2. Go from front to back in both numbers: res[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
3. Now go from back to front in res array and propagate carry from back to front
        for (int i = res.length - 1; i >= 1; --i) {
            res[i - 1] += res[i] / 10;
            res[i] %= 10;
        }
4. Concatenate all numbers in the res array
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";

        int[] res = new int[num1.length() + num2.length() - 1];

        for (int i = 0; i < num1.length(); ++i) {
            for (int j = 0; j < num2.length(); ++j) {
                res[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        for (int i = res.length - 1; i >= 1; --i) {
            res[i - 1] += res[i] / 10;
            res[i] %= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            sb.append(num);
        }
        return sb.toString();
    }
}
