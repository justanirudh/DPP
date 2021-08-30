package com.anirudh.interview_prep_2021.facebook.lc_last_6m;

/**
 * Created by paanir on 8/26/21.
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 Medium

 2595

 56

 Add to List

 Share
 Given a string s of '(' , ')' and lowercase English characters.

 Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 parentheses string is valid and return any valid string.

 Formally, a parentheses string is valid if and only if:

 It is the empty string, contains only lowercase characters, or
 It can be written as AB (A concatenated with B), where A and B are valid strings, or
 It can be written as (A), where A is a valid string.


 Example 1:

 Input: s = "lee(t(c)o)de)"
 Output: "lee(t(c)o)de"
 Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 Example 2:

 Input: s = "a)b(c)d"
 Output: "ab(c)d"
 Example 3:

 Input: s = "))(("
 Output: ""
 Explanation: An empty string is also valid.
 Example 4:

 Input: s = "(a(b(c)d)"
 Output: "a(b(c)d)"


 Constraints:

 1 <= s.length <= 10^5
 s[i] is one of  '(' , ')' and lowercase English letters.
 */

//Do Until Binary Tree Maximum Path Sum for FB prep for <6m

/**
 * normal push/pop for valid paren
 */
public class MinimumRemovetoMakeValidParentheses {

    class Paren {
        char paren;
        int index;
        Paren(char paren, int index) {
            this.paren = paren;
            this.index = index;
        }
    }

    public String minRemoveToMakeValid(String s) {
        Deque<Paren> parens = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(c == '(' || c == ')'){
                if(c == '(') {
                    parens.push(new Paren('(', i));
                }
                else { //if it is closed, check if top of stack is open, if yes, pop it out
                    if(!parens.isEmpty() && parens.peek().paren == '(') {
                        parens.pop();
                    }
                    else {
                        parens.push(new Paren(')', i));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder(s);
        while(!parens.isEmpty()) {
            sb.deleteCharAt(parens.pop().index);
        }
        return sb.toString();

    }
}