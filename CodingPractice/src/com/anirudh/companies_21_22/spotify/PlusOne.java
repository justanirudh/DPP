package com.anirudh.companies_21_22.spotify;

/**
 * Created by paanir on 6/9/17.
 */
/*
66. Plus One

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.
 */

/*
Use textbook math
*/
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            int num = digits[i] + carry;
            if (num > 9) {
                carry = 1;
                digits[i] = num % 10;
            } else {
                carry = 0;
                digits[i] = num;
            }
        }
        if (carry == 0)
            return digits;
        else {
            int[] dest = new int[digits.length + 1];
            System.arraycopy(digits, 0, dest, 1, digits.length);
            dest[0] = 1;
            return dest;
        }
    }
}
