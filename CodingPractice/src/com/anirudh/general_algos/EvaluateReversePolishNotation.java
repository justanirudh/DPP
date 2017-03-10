package com.anirudh.general_algos;

import java.util.Stack;

/**
 * Created by paanir on 3/9/17.
 */
/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 0)
            return 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; ++i) {
            String curr = tokens[i];
            if (curr.equals("+") || curr.equals("-") || curr.equals("/") || curr.equals("*")) {
                int right = Integer.parseInt(stack.pop());
                int left = Integer.parseInt(stack.pop());
                if (curr.equals("+"))
                    stack.push(Integer.toString(left + right));
                else if (curr.equals("-"))
                    stack.push(Integer.toString(left - right));
                else if (curr.equals("/"))
                    stack.push(Integer.toString(left / right));
                else
                    stack.push(Integer.toString(left * right));
            } else
                stack.push(curr);
        }
        return Integer.parseInt(stack.pop());
    }
}
