package com.anirudh.companies_21_22.jane_street;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
227. Basic Calculator II
Medium

3931

522

Add to List

Share
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5


Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */

/*
Keep 2 stacks:
operator stack
operand stack
when number: put in operator stack
    if multi-digit num, handle it
when operator
    check if the operator stack has a higher pri operator at the top
        if so, operate on the 2 stacks and pop and push new values
        if not, just push the higher pri operator
At the end of the string
    keep operating until operator stack is empty
 */
public class BasicCalculatorII {
    Map<Character, Integer> pri;

    int operate(int a, int b, char op) {
        int res;
        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                res = a / b;
                break;
            default:
                throw new RuntimeException("op not supported");
        }
        return res;
    }

    Map<Character, Integer> createPri() {
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 0);
        precedence.put('-', 0);
        precedence.put('/', 1);
        precedence.put('*', 1);
        return precedence;
    }

    public int calculate(String s) {
        Deque<Character> operators = new ArrayDeque<>();
        Deque<Integer> operands = new ArrayDeque<>();
        pri = createPri();

        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (Character.isDigit(c)) { //operand
                int num = 0;
                while (i < chars.length && Character.isDigit(chars[i])) {
                    num = num * 10 + (chars[i] - '0');
                    i++;
                }
                operands.push(num);
            } else if (c == ' ') {
                i++;
            } else { //operator
                while (!operators.isEmpty() && pri.get(operators.peek()) - pri.get(c) > 0) { //evaulate higher/same priority ops in stack first
                    char op = operators.pop();
                    int b = operands.pop();
                    int a = operands.pop();
                    int num = operate(a, b, op);
                    operands.push(num);
                }

                operators.push(c);
                i++;
            }
        }
        while (!operators.isEmpty()) {
            char op = operators.pop();
            int b = operands.pop();
            int a = operands.pop();
            int num = operate(a, b, op);
            operands.push(num);
        }

        return operands.pop();
    }
}
