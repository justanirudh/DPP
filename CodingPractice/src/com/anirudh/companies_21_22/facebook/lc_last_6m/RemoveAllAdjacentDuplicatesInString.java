package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.ArrayDeque;
import java.util.Deque;

/*
1047. Remove All Adjacent Duplicates In String
Easy

2810

147

Add to List

Share
You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.



Example 1:

Input: s = "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
Example 2:

Input: s = "azxxzy"
Output: "ay"


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
Accepted
254,426
Submissions
358,208
 */

/*
Keep a stack, remove whenever next char is same as peek
 */
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
