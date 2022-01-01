package com.anirudh.companies_21_22.google.lc_last_6m;

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
Use stack
 */
public class DecodeString {

    boolean isPositiveNumber(String str) {
        for(char c : str.toCharArray()) {
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    public String decodeString(String s) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> sList = new ArrayList<>();
        StringBuilder sb;
        for (Character c : s.toCharArray()) {
            sList.add(c.toString());
        }
        for (String str : sList) {
            if (!str.equals("]")) {
                if (isPositiveNumber(str)) {
                    if (!stack.isEmpty() && isPositiveNumber(stack.peek())) { //add to already existing number
                        String num = stack.pop();
                        stack.push(num + str);
                    } else { //just push the number
                        stack.push(str);
                    }
                } else { //just push for first number, [ and alphabets
                    stack.push(str);
                }
            } else {
                sb = new StringBuilder(); // to get string to be repeated
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    String top = stack.pop();
                    sb.insert(0, top);
                }
                String bracketString = sb.toString();
                stack.pop(); //pop [
                int count = Integer.parseInt(stack.pop()); //number of repetition
                sb = new StringBuilder(); //to get final string
                while (count != 0) {
                    sb.append(bracketString);
                    count--;
                }
                stack.push(sb.toString());
            }
        }
        sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
