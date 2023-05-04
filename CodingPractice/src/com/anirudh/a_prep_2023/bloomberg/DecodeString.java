package com.anirudh.a_prep_2023.bloomberg;

/*
394. Decode String
Medium

6508

287

Add to List

Share
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
Use stack of chars
 */
public class DecodeString {
    public String decodeString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char a : s.toCharArray()) {
            if (a != ']') {
                stack.push(a);
            } else {
                //match opening, get number, repeat chars, put back in
                List<Character> inBrackets = new ArrayList<>();
                while (stack.peek() != '[') { //get chars in bracket
                    inBrackets.add(stack.pop());
                }
                stack.pop(); //pop [
                StringBuilder timesS = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) { //construct number
                    timesS.insert(0, stack.pop());
                }
                int times = Integer.parseInt(timesS.toString());
                for (int i = 0; i < times; ++i) { //put chars back in correct order
                    for (int j = inBrackets.size() - 1; j >= 0; --j) {
                        stack.push(inBrackets.get(j));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();

    }

}
