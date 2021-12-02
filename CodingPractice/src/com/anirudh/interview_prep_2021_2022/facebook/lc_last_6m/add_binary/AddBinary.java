package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m.add_binary;



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

import java.util.HashMap;
import java.util.Map;

/**
 * Do addition like we do for decimals (textbook math), but for binary. With carry and everything in binary
 * Add normally then use decimalToBinary table to carry:
 * sumMap.put(0, "00");
 * sumMap.put(1, "01");
 * sumMap.put(2, "10");
 * sumMap.put(3, "11");
 *
 * Space: O(Max(a.len,b.len)), Time: O(Max(a.len,b.len))
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        String ar = new StringBuilder(a).reverse().toString();
        String br = new StringBuilder(b).reverse().toString();
        //start adding
        //make carry map
        Map<Integer, String> sumMap = new HashMap<>();
        sumMap.put(0, "00");
        sumMap.put(1, "01");
        sumMap.put(2, "10");
        sumMap.put(3, "11");

        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int maxLen = Math.max(ar.length(), br.length());
        for (int i = 0; i < maxLen; ++i) {
            int arb = (i < ar.length()) ? ar.charAt(i) - '0' : 0;
            int brb = (i < br.length()) ? br.charAt(i) - '0' : 0;
            int sum = arb + brb + carry;
            String sumb = sumMap.get(sum);
            sb.append(sumb.charAt(1));
            carry = sumb.charAt(0) - '0';
        }
        if (carry == 1)
            sb.append("1");
        return sb.reverse().toString();
    }
}
