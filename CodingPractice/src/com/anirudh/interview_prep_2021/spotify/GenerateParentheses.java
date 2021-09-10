package com.anirudh.interview_prep_2021.spotify;

import java.util.ArrayList;
import java.util.List;

/*
22. Generate Parentheses
Medium

9594

385

Add to List

Share
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 */

/*
Intuition and Algorithm

Instead of adding '(' or ')' every time as in Approach 1, let's only add them when we know it will remain a valid sequence. We can do this by keeping track of the number of opening and closing brackets we have placed so far.

We can start an opening bracket if we still have one (of n) left to place. And we can start a closing bracket if it would not exceed the number of opening brackets.

Use backtracking

if(open < max) {
    add open
    backtrack
    remove open
}
if(close < open) {
    add close
    backtrack
    remove close
}

time cx is complicated
 */
public class GenerateParentheses {

    List<String> res;
    int n;

    void backtrack(StringBuilder curr, int open, int close) {
        if(curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }

        if(open < n) {
            curr.append("(");
            backtrack(curr, open + 1, close);
            curr.deleteCharAt(curr.length() - 1);
        }
        if(close < open) {
            curr.append(")");
            backtrack(curr, open, close + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        this.n = n;
        backtrack(new StringBuilder(), 0, 0);
        return res;
    }
}
