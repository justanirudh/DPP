package com.anirudh.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
    class RPN {
        private Stack<String> stack;
        private String[] equation;
        private Set<String> operators;

        RPN(String[] s) {
            stack = new Stack<>();
            this.equation = s;
            operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        }

        private int evaluate(String op) {
            int right = Integer.parseInt(stack.pop());
            int left = Integer.parseInt(stack.pop());
            switch (op) {
                case "+":
                    return left + right;
                case "-":
                    return left - right;
                case "*":
                    return left * right;
                case "/":
                    return left / right;
                default:
                    throw new RuntimeException("unknown symbol");
            }
        }

        public int evaluate() {
            for (String s : equation) {
                if (operators.contains(s))
                    s = Integer.toString(evaluate(s));
                stack.push(s);
            }
            return Integer.parseInt(stack.pop());
        }
    }

    public int evalRPN(String[] tokens) {
        RPN rpn = new RPN(tokens);
        return rpn.evaluate();
    }
}
