package com.anirudh.companies_21_22.facebook.lc_last_6m.tbd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by paanir on 8/28/21.
 */
/*
301. Remove Invalid Parentheses
Hard

3801

179

Add to List

Share
Given a string number that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.



Example 1:

Input: number = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: number = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: number = ")("
Output: [""]
 */

/**
 * TODO: To be understood; Implement
 * https://leetcode.com/problems/remove-invalid-parentheses/solution/
 */
class RemoveInvalidParentheses {

    private Set<String> validExpressions = new HashSet<>();
    private int minimumRemoved;

    private void reset() {
        this.validExpressions.clear();
        this.minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            StringBuilder expression,
            int removedCount) {

        // If we have reached the end of string.
        if (index == s.length()) {

            // If the current expression is valid.
            if (leftCount == rightCount) {

                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= this.minimumRemoved) {

                    // Convert StringBuilder to a String. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        } else {

            char currentCharacter = s.charAt(index);
            int length = expression.length();

            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {

                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);

                // If it'number an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }

                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length);
            }
        }
    }

    public List<String> removeInvalidParentheses(String s) {

        this.reset();
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<>(this.validExpressions);
    }
}
