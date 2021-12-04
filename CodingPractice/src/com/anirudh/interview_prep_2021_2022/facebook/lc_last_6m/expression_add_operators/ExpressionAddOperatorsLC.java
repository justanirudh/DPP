package com.anirudh.interview_prep_2021_2022.facebook.lc_last_6m.expression_add_operators;

/**
 * Created by paanir on 8/29/21.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Hard
 * <p>
 * 1859
 * <p>
 * 318
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators
 * '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 */

/*
 - Do efficient Brute force, no other way to solve this. Use recursion and backtracking to do this.
 - Add another NOOP operator to lump digits together

 For the function, pass in these params:
 1. Index: index of the string
 2. exp: expression we are building
 3. solution: this should become target at the end
 4. curr: used for NOOP only. current number that can consist of multiple digits and is needed because we have the NOOP operation
 5. prev: used for multi only. Used to undo previous operation as multi takes precedence

 For each operator:
 1. Add to exp
 2. Add/subtract/multi to solution
 3. recurse
 4. backtrack by removing the last 2 chars ( operation and operand)

 The NOOP operator just iterates the index do that another digit is included

 At the end, if index reaches the end of the string and teh solution is equal to target, add it to answer

 T = O(n * 4^n): 4 choices in each digit gets to 4^n. In base case we create a string of size N. Hence for 4^n strings, O(n * 4^n)
 S = O(N) for stack

 */
public class ExpressionAddOperatorsLC {

    public List<String> answer;
    public String digits;
    public long target;

    void recurse(
            int index,
            long prev, // used to make sure we backtrack if we use multiplication
            long curr, //used by NOOP
            long solution,
            ArrayList<String> exp) {

        // Done processing all the digits in num
        if (index == digits.length()) {
            // If the final value == target expected AND no operand is left unprocessed
            if (solution == target && curr == 0) {
                StringBuilder sb = new StringBuilder();
                for(int i = 1; i < exp.size(); ++i) { //Taking from 1 because first character is always a +
                    sb.append(exp.get(i));
                }
                answer.add(sb.toString());
            }
            return;
        }

        curr = curr * 10 + Character.getNumericValue(digits.charAt(index));

        // NOOP: Extending the current operand by one digit
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
