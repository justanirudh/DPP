package com.anirudh.a_prep_2023.bloomberg;

import java.util.ArrayDeque;
import java.util.Deque;

/*
1209. Remove All Adjacent Duplicates in String II
Medium
5.1K
98
company
Bloomberg
company
Amazon
company
Goldman Sachs
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lowercase English letters.
 */

/*
Store char with the freq in the stack
Increase freq if peek is same elem
When find kth elem pop k-1 elems
 */
public class RemoveAllAdjacentDuplicatesinStringII {
    class CharFreq {
        char c;
        int freq;

        CharFreq(char ch, int freq) {
            this.c = ch;
            this.freq = freq;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<CharFreq> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()) {
                if (stack.peek().c != c) {
                    stack.push(new CharFreq(c, 1));
                } else { //peek is same elem
                    if (stack.peek().freq == k - 1) { //pop-pop
                        for (int i = 0; i < k - 1; ++i) {
                            stack.pop();
                        }
                    } else {
                        stack.push(new CharFreq(c, stack.peek().freq + 1));
                    }
                }
            } else {
                stack.push(new CharFreq(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop().c);
        }
        return sb.toString();
    }
}
