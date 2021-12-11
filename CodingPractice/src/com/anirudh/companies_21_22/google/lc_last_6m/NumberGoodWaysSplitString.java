package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paanir on 9/3/21.
 */
/*
1525. Number of Good Ways to Split a String
Medium

785

22

Add to List

Share
You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.

Return the number of good splits you can make in s.



Example 1:

Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
Example 2:

Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").
Example 3:

Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.
Example 4:

Input: s = "acbadbaada"
Output: 2
 */

/*
O(n) space
O(n) time
Create chracter frquency maps. populate them with 1:n-1 split
Then for all other splits, keep transferring rightmost element from right string to left string and calculating new maps
Each time compare sizes of the maps, if they are equal, it is a good split
 */
public class NumberGoodWaysSplitString {
    public int numSplits(String s) {
        Map<Character, Integer> left = new HashMap<>(); //character frequencies
        Map<Character, Integer> right = new HashMap<>();

        int res = 0;

        //base case
        left.put(s.charAt(0), 1);
        for(int i = 1; i < s.length(); ++i) {
            right.put(s.charAt(i), right.getOrDefault(s.charAt(i), 0) + 1);
        }
        if(left.size() == right.size())
            res++;

        for(int i = 1; i < s.length() - 1; ++i) {
            char charLToR = s.charAt(i); // char being transferred from right to Left
            left.put(charLToR, left.getOrDefault(charLToR, 0) + 1);
            right.put(charLToR, right.get(charLToR) - 1);

            if(right.get(charLToR) == 0)
                right.remove(charLToR);

            if(left.size() == right.size())
                res++;

        }

        return res;
    }
}
