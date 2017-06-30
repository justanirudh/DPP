package com.anirudh.general_algos;

import java.util.Stack;

/**
 * Created by paanir on 6/29/17.
 */
/*
20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(': {
                    st.push(c);
                    break;
                }
                case ')': {
                    if (st.isEmpty() || st.peek() != '(')
                        return false;
                    else
                        st.pop();
                    break;
                }
                case '{': {
                    st.push(c);
                    break;
                }
                case '}': {
                    if (st.isEmpty() || st.peek() != '{')
                        return false;
                    else
                        st.pop();
                    break;
                }
                case '[': {
                    st.push(c);
                    break;
                }
                case ']': {
                    if (st.isEmpty() || st.peek() != '[')
                        return false;
                    else
                        st.pop();
                    break;
                }
            }
        }
        return st.isEmpty();
    }
}
