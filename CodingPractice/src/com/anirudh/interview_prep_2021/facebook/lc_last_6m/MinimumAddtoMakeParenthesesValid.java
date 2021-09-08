package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by paanir on 8/29/21.
 */
/*
921. Minimum Add to Make Parentheses Valid
Medium

1529

92

Add to List

Share
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string number. In one move, you can insert a parenthesis at any position of the string.

For example, if number = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make number valid.

Example 1:

Input: number = "())"
Output: 1
Example 2:

Input: number = "((("
Output: 3
Example 3:

Input: number = "()"
Output: 0
Example 4:

Input: number = "()))(("
Output: 4
 */

/*
Do clever balance and rightUnopen bracket calc: O(1) space O(n) time
Do the stack solution for finding unmatched parens: O(n) space O(n) time

int balance = 0; //unclosed left brackets, finally
int unopenedRight = 0; //unopened right brackets

 */
public class MinimumAddtoMakeParenthesesValid {

    //O(1) space, O(n) time
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int unopenedRight = 0;

        for (char c : s.toCharArray()) {
            balance += (c == '(') ? 1 : -1;

            if (balance == -1) { //means at least 1 right bracket that was not opened
                unopenedRight++;
                balance++;
            }
        }
        return unopenedRight + balance;

    }

    //O(n) space, O(n) time
    public int minAddToMakeValidParen(String s) {
        Deque<Character> parens = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                parens.push('(');
            } else { //if it is closed, check if top of stack is open, if yes, pop it out
                if (!parens.isEmpty() && parens.peek() == '(') {
                    parens.pop();
                } else {
                    parens.push(')');
                }
            }
        }
        return parens.size();
    }

}
