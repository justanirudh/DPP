package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m.add_binary;

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
public class AddBinaryOverflowButEasy {


    //Easy but will still overflow for bigger numbers
    public String addBinary(String a, String b) {
        int aDecimal = Integer.parseInt(a, 2);
        int bDecimal = Integer.parseInt(b, 2);
        return Integer.toBinaryString( aDecimal+ bDecimal);
    }

}
