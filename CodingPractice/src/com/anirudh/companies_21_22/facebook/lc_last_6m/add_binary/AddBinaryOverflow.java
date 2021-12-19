package com.anirudh.companies_21_22.facebook.lc_last_6m.add_binary;

/**
 * Created by paanir on 8/29/21.
 */

/**
 * 67. Add Binary
 Easy

 3295

 392

 Add to List

 Share
 Given two binary strings a and b, return their sum as a binary string.



 Example 1:

 Input: a = "11", b = "1"
 Output: "100"
 Example 2:

 Input: a = "1010", b = "1011"
 Output: "10101"
 */

//Overflows, cannot handle huge Ints
public class AddBinaryOverflow {

    int binaryToDecimal(String a) {
        int sum = 0;
        int len = a.length();
        for(int i = 0; i < len; ++i) {
            sum += (a.charAt(i) - '0') * Math.pow(2, len - 1 - i);
        }
        return sum;
    }

    String decimalToBinary(int num) {
        if(num == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            int quotient = num/2;
            int remainder = num %2;
            sb.insert(0, remainder);
            num = quotient;
        }
        return sb.toString();
    }

    public String addBinary(String a, String b) {
        int aDecimal = binaryToDecimal(a);

        int bDecimal = binaryToDecimal(b);
        return decimalToBinary(aDecimal + bDecimal);
    }
}
