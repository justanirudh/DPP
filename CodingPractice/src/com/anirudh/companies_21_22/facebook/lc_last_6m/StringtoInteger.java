package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
8. String to Integer (atoi)
Medium

930

2717

Add to List

Share
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

Read in and ignore any leading whitespace.
Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
Return the integer as the final result.
Note:

Only the space character ' ' is considered a whitespace character.
Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.


Example 1:

Input: s = "42"
Output: 42
Explanation: The underlined characters are what is read in, the caret is the current reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^
The parsed integer is 42.
Since 42 is in the range [-231, 231 - 1], the final result is 42.
Example 2:

Input: s = "   -42"
Output: -42
Explanation:
Step 1: "   -42" (leading whitespace is read and ignored)
            ^
Step 2: "   -42" ('-' is read, so the result should be negative)
             ^
Step 3: "   -42" ("42" is read in)
               ^
The parsed integer is -42.
Since -42 is in the range [-231, 231 - 1], the final result is -42.
Example 3:

Input: s = "4193 with words"
Output: 4193
Explanation:
Step 1: "4193 with words" (no characters read because there is no leading whitespace)
         ^
Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
             ^
The parsed integer is 4193.
Since 4193 is in the range [-231, 231 - 1], the final result is 4193.


Constraints:

0 <= s.length <= 200
s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */

/*
Create DFA
Use good logic to find if we reached INT_MAX/INT_MIN
 */
public class StringtoInteger {

    int res;
    boolean isPositive;

    String processDigit(int d) {
        if (res > Integer.MAX_VALUE / 10 //if res > max/10 => res * 10 > max => res * 10 + d > max => new number that will be created > max
                || (res == Integer.MAX_VALUE / 10 && d > Integer.MAX_VALUE % 10)) { //similar to above but for d
            if (isPositive) {
                res = Integer.MAX_VALUE;
            } else {
                res = Integer.MIN_VALUE;
                isPositive = true; //so that at the end we dont multiple -ve * -ve to make it positive again
            }
            return "default"; //halt execution
        } else { //happy case
            res = res * 10 + d;
            return "digit";
        }

    }

    public int myAtoi(String s) {
        List<Map<String, Integer>> dfa = new ArrayList<>();

        Map<String, Integer> state0 = new HashMap<>(); //initial state
        state0.put("space", 0);
        state0.put("sign", 1);
        state0.put("digit", 2);
        state0.put("default", 3);
        dfa.add(state0);

        Map<String, Integer> state1 = new HashMap<>();
        state1.put("digit", 2);
        state1.put("default", 3);
        dfa.add(state1);

        Map<String, Integer> state2 = new HashMap<>(); //final state
        state2.put("digit", 2);
        state2.put("default", 3);
        dfa.add(state2);

        Map<String, Integer> state3 = new HashMap<>(); //dead state
        state3.put("default", 3);
        dfa.add(state3);

        res = 0;
        isPositive = true;
        int state = 0;

        for (char c : s.toCharArray()) {
            if (state == 3)
                break;
            Map<String, Integer> transitions = dfa.get(state);
            String transition;
            if (c == ' ')
                transition = "space";
            else if (Character.isDigit(c)) {
                transition = processDigit(c - '0');
            } else if (c == '+' || c == '-') {
                transition = "sign";
                if (transitions.containsKey(transition) && c == '-') //only if it is a valid transition
                    isPositive = false;
            } else
                transition = "default";

            state = transitions.getOrDefault(transition, 3); //if transition doesnt exist, go to dead state
        }

        return isPositive ? res : -1 * res;

    }
}
