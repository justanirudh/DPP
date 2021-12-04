package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.ArrayDeque;
import java.util.Deque;

/*
150. Evaluate Reverse Polish Notation
Medium

2135

559

Add to List

Share
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.



Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */

/*
RPN: Left to Right Stack
NPN: Right to Left Stack

Now, what if you wanted to support arbitrary operations on some variable number of preceding numbers? How does the code change?
If arity is given for each operator: it would just be a question of how many numbers to pop
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (!token.equals("+") &&
                    !token.equals("-") &&
                    !token.equals("/") &&
                    !token.equals("*"))
                stack.push(Integer.parseInt(token));
            else {
                int rightOp = stack.pop();
                int leftOp = stack.pop();
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(leftOp + rightOp);
                        break;
                    case '-':
                        stack.push(leftOp - rightOp);
                        break;
                    case '*':
                        stack.push(leftOp * rightOp);
                        break;
                    case '/':
                        stack.push(leftOp / rightOp);
                        break;
                    case '&': //example of arity of 3
                        int leftleftOp = stack.pop();
                        stack.push(leftleftOp + leftOp + rightOp);
                        break;
                    default:
                        throw new RuntimeException("Unsupported operator");
                }
            }
        }
        return stack.pop();
    }
}
