package com.anirudh.companies_21_22.facebook.lc_last_6m;

/**
 * Created by paanir on 8/26/21.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Medium
 * <p>
 * 2595
 * <p>
 * 56
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string number of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: number = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 * <p>
 * Input: number = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 * <p>
 * Input: number = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 * <p>
 * Input: number = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= number.length <= 10^5
 * number[i] is one of  '(' , ')' and lowercase English letters.
 */

/**
 * normal push/pop for valid paren
 */
public class MinimumRemovetoMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        Deque<Integer> parens = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                if (c == '(') {
                    parens.push(i);
                } else { //if it is closed, check if top of stack is open, if yes, pop it out
                    if (!parens.isEmpty() && s.charAt(parens.peek()) == '(') {
                        parens.pop();
                    } else {
                        parens.push(i);
                    }
                }
            }
        }

        Set<Integer> remove = new HashSet<>();
        while (!parens.isEmpty()) {
            remove.add(parens.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if(!remove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

    }
    /*
        public String minRemoveToMakeValid(String s) {
        int balance = 0;
        int unopenedRight = 0;

        for (char c : s.toCharArray()) {
            if(c == '(')
                balance++;
            else if (c == ')')
                balance--;
            else
                continue;

            if (balance == -1) { //means at least 1 right bracket that was not opened
                unopenedRight++;
                balance++;
            }
        }
        return unopenedRight + balance;
    }
     */
}
