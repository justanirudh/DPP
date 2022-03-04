package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayList;
import java.util.List;

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
/*
Do Sliding window!

start with {st, slowS, fastS, open, closed}
fast goes from start to end of str
    whenever num_close > num_open
        for the window slow to fast, if the parents closed && (if its first paren or its prev was not closed)
            delete the closed paren
            recurse with new string, slow, fast, open, close
        return at the end of loop
now reverse string and do same for open brackets
if(opn == ()
    means need to do reverse analysis
else
    add to result

Use DFS: https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
 */
class RemoveInvalidParentheses {

    List<String> output;

    public void removeHelper(String res, int slowStart, int fastStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        for (int fast = fastStart; fast < res.length(); fast++) {
            if (res.charAt(fast) == openParen)
                numOpenParen++;
            if (res.charAt(fast) == closedParen)
                numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int slow = slowStart; slow <= fast; slow++) {
                    // Try removing one at each position, skipping duplicates
                    if (res.charAt(slow) == closedParen && (slow == slowStart || res.charAt(slow - 1) != closedParen)) {
                        //remove jth index
                        String newS = new StringBuilder(res).deleteCharAt(slow).toString();
                        // Recursion: iStart = i since we now have valid # closed parenthesis thru i. slowStart = j prevents duplicates
                        removeHelper(newS, slow, fast, openParen, closedParen); //slow has the next char of the deleted char
                    }
                }
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(res).reverse().toString();
        if (openParen == '(') //means we have only looked from left to right
            removeHelper(reversed, 0, 0, ')', '('); //now do rToL
        else
            output.add(reversed); //looked at both lToR and rToL, reversed of reversed is original string
    }

    public List<String> removeInvalidParentheses(String s) {
        output = new ArrayList<>();
        removeHelper(s, 0, 0, '(', ')');
        return output;
    }
}
