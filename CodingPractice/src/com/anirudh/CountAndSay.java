package com.anirudh;

/**
 * Created by paanir on 12/31/16.
 */
/*
38. Count and Say
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
1211 -> 111221
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {

    public static String countAndSay(int n) {
        if (n <= 0)
            return "";
        else if (n == 1)
            return "1";
        else {
            String previous = countAndSay(n - 1);
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < previous.length()) {
                Character curr = previous.charAt(i);
                int currCount = 0;
                while (i < previous.length() && previous.charAt(i) == curr) {
                    currCount++;
                    i++;
                }
                sb.append(currCount);
                sb.append(curr);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
