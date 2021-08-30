package com.anirudh.interview_prep_2021.facebook.lc_last_6m.expression_add_operators;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 Hard

 1859

 318

 Add to List

 Share
 Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.


 Example 1:

 Input: num = "123", target = 6
 Output: ["1*2*3","1+2+3"]
 Example 2:

 Input: num = "232", target = 8
 Output: ["2*3+2","2+3*2"]
 Example 3:

 Input: num = "105", target = 5
 Output: ["1*0+5","10-5"]
 Example 4:

 Input: num = "00", target = 0
 Output: ["0*0","0+0","0-0"]
 Example 5:

 Input: num = "3456237490", target = 9191
 Output: []
 */
public class ExpressionAddOperatorsLC {

    public List<String> answer;
    public String digits;
    public long target;

    public void recurse(
            int index,
            long prev, // used to make sure we backtrack if we use multiplication
            long curr, //used to make sure we dont do NOOP operator on numbers starting with 0
            long solution,
            ArrayList<String> exp) {

        // Done processing all the digits in num
        if (index == digits.length()) {
            // If the final value == target expected AND no operand is left unprocessed
            if (solution == target && curr == 0) {
                StringBuilder sb = new StringBuilder();
                exp.subList(1, exp.size()).forEach(v -> sb.append(v)); //Taking from 1 because first character is always a +
                answer.add(sb.toString());
            }
            return;
        }

        // NOOP: Extending the current operand by one digit
        curr = curr * 10 + Character.getNumericValue(digits.charAt(index));

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a valid operand. Hence this check
        if (curr > 0) {
            recurse(index + 1, prev, curr, solution, exp);
        }

        String currStr = Long.toString(curr);
        // ADDITION
        exp.add("+");
        exp.add(currStr);
        recurse(index + 1, curr, 0, solution + curr, exp);
        exp.remove(exp.size() - 1); //backtracking: popping operand
        exp.remove(exp.size() - 1); //backtracking: popping + sign

        if (exp.size() > 0) {

            // SUBTRACTION
            exp.add("-");
            exp.add(currStr);
            recurse(index + 1, -curr, 0, solution - curr, exp);
            exp.remove(exp.size() - 1);
            exp.remove(exp.size() - 1);

            // MULTIPLICATION
            exp.add("*");
            exp.add(currStr);
            recurse(
                    index + 1,
                    curr * prev,
                    0,
                    solution - prev + (curr * prev),
                    exp);
            exp.remove(exp.size() - 1);
            exp.remove(exp.size() - 1);
        }
    }

    public List<String> addOperators(String num, int target) {

        if (num.length() == 0) {
            return new ArrayList<>();
        }

        this.target = target;
        this.digits = num;
        this.answer = new ArrayList<>();
        this.recurse(0, 0, 0, 0, new ArrayList<>());
        return this.answer;
    }
}
