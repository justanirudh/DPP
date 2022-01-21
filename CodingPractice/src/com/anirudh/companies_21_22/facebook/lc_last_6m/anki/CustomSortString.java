package com.anirudh.companies_21_22.facebook.lc_last_6m.anki;

import java.util.*;

/*
791. Custom Sort String
Medium

1734

265

Add to List

Share
You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.



Example 1:

Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
Example 2:

Input: order = "cbafg", s = "abcd"
Output: "cbad"


Constraints:

1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */

/*
Make freq map of Map {c -> frequency} from s;
Now go in order in "order" string, find it in map and add it to result that many number of times
    remove from map after adding
After going through order, go through the remaining map and act like you dont care
 */
public class CustomSortString {

    public String customSortString(String order, String s) {

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (freqMap.containsKey(c)) {
                int freq = freqMap.get(c);
                for (int i = 0; i < freq; ++i) {
                    sb.append(c);
                }
                freqMap.remove(c);
            }
        }
        //now go through the rest and add to sb
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; ++i) {
                sb.append(c);
            }
        }
        return sb.toString();

    }
}
