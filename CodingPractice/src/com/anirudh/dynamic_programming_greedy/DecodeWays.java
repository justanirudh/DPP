package com.anirudh.dynamic_programming_greedy;

/**
 * Created by paanir on 9/4/17.
 */
/*
91. Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.equals(""))
            return 0;
        char[] chars = s.toCharArray();
        int[] ways = new int[s.length()];
        //ways[i] = number of ways to decode string till i (included)
        //ways[s.length() - 1] will be answer
        ways[0] = chars[0] != '0' ? 1 : 0; //if it is non-zero, it gets decoded
        for (int i = 1; i < s.length(); ++i) {
            //single digit
            if (chars[i] != '0') //chars i one behind ways i
                ways[i] = ways[i - 1]; //if non-zero, then at least number of ways till i-1 valid as chars[i] also valid
            //double digit
            int doubleDigit = Integer.parseInt(new StringBuilder().append(chars[i - 1]).append(chars[i]).toString());
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                if (i - 2 >= 0)
                    ways[i] += ways[i - 2]; //all ways till i-2 also valid as chars[i-1]chars[i] is also an integer
                else //if string is shorter
                    ways[i] += 1; //the double digit is the entire string
            }
        }
        return ways[s.length() - 1];
    }
}
