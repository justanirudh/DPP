package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.HashMap;
import java.util.Map;

/*
13. Roman to Integer
Easy

1807

136

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
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Example 2:

Input: s = "IV"
Output: 4
Example 3:

Input: s = "IX"
Output: 9
Example 4:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

/*
option 1 below: check 1-len and 2-len symbols. Use switch-case
option 2: in a loop, check if 2-len is in the symbol -> integer map, if not pick 1-len
 */
public class RomanToInteger {
    Map<String, Integer> dict = new HashMap<>();

    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case 'V':
                    sum += 5;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
                case 'I': {
                    if(i == s.length() - 1) {
                        sum += 1;
                        break;
                    }
                    i++;
                    char cNext = s.charAt(i);
                    switch (cNext) {
                        case 'V':
                            sum += 4;
                            break;
                        case 'X':
                            sum += 9;
                            break;
                        default:
                            sum += 1;
                            i--;
                    }
                    break;
                }
                case 'X': {
                    if(i == s.length() - 1) {
                        sum += 10;
                        break;
                    }
                    i++;
                    char cNext = s.charAt(i);
                    switch (cNext) {
                        case 'L':
                            sum += 40;
                            break;
                        case 'C':
                            sum += 90;
                            break;
                        default:
                            sum += 10;
                            i--;
                    }
                    break;
                }
                case 'C': {
                    if(i == s.length() - 1) {
                        sum += 100;
                        break;
                    }
                    i++;
                    char cNext = s.charAt(i);
                    switch (cNext) {
                        case 'D':
                            sum += 400;
                            break;
                        case 'M':
                            sum += 900;
                            break;
                        default:
                            sum += 100;
                            i--;
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException();

            }
        }
        return sum;
    }
}
