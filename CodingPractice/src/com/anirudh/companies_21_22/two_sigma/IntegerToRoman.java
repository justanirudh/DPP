package com.anirudh.companies_21_22.two_sigma;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/*
12. Integer to Roman
Medium

2266

3526

Add to List

Share
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.



Example 1:

Input: num = 3
Output: "III"
Example 2:

Input: num = 4
Output: "IV"
Example 3:

Input: num = 9
Output: "IX"
Example 4:

Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
/*
Reverse sort valid integers
Go through the valid_integers array and GREEDILY create roman string by subtracting from original num
Keep staying at a valid_integer until it becomes > num
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        Map<Integer, String> validNumbers = new TreeMap<>(Comparator.reverseOrder());
        validNumbers.put(1, "I");
        validNumbers.put(5, "V");
        validNumbers.put(10, "X");
        validNumbers.put(50, "L");
        validNumbers.put(100, "C");
        validNumbers.put(500, "D");
        validNumbers.put(1000, "M");
        validNumbers.put(4, "IV");
        validNumbers.put(9, "IX");
        validNumbers.put(40, "XL");
        validNumbers.put(90, "XC");
        validNumbers.put(400, "CD");
        validNumbers.put(900, "CM");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : validNumbers.entrySet()) {
            if (num == 0)
                break;
            while (entry.getKey() <= num) {
                num -= entry.getKey();
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }
}
