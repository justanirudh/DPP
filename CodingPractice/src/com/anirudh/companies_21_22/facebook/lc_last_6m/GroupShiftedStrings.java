package com.anirudh.companies_21_22.facebook.lc_last_6m;

import java.util.*;

/**
 * Created by paanir on 8/30/21.
 */
/*
249. Group Shifted Strings
Medium

869

170

Add to List

Share
We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
Example 2:

Input: strings = ["a"]
Output: [["a"]]
 */

/**
 * 1. Create a Map of {(string length) -> Map {string -> all strings in its sequence } }
 * 2. For any new string, first compare length, then compare difference between each character in string
 * 3. If diff in each character is same, add it to value of the map In (abc, xyz), a-x == b-y == c-z
 * 4. Catch: list is circular as `a` comes right after `z`. For that, any time the diff is -ve, make it +ve by adding 26
 **/
public class GroupShiftedStrings {

    boolean isSameSequence(String s1, String s2) { //s1 and s2 of same length
        Set<Integer> singleton = new HashSet<>();
        for (int i = 0; i < s1.length(); ++i) {
            int diff = s2.charAt(i) - s1.charAt(i);
            if(diff < 0)
                diff += 26; //creates the circular behaviour
            singleton.add(diff);
            if (singleton.size() > 1)
                return false;
        }
        return true;
    }

    public List<List<String>> groupStrings(String[] strings) {

        //map of length of string to map <string -> all strings in the sequence>
        Map<Integer, Map<String, List<String>>> seqs = new HashMap<>();

        for (String string : strings) {
            int len = string.length();

            if (seqs.containsKey(len)) { //if same length string found
                // go through each string of same size to find a sequence
                Map<String, List<String>> internalMap = seqs.get(len);
                boolean found = false;
                for (String key : internalMap.keySet()) {
                    boolean isSameSeq = isSameSequence(string, key);
                    if (isSameSeq) {
                        internalMap.get(key).add(string);
                        found = true;
                        break;
                    }
                }
                if (found)
                    continue; //go to next string
                //If here, this means not in same sequence as other strings of same length. Make a new entry
                List<String> list = new ArrayList<>();
                list.add(string);
                internalMap.put(string, list);
            } else { //make a new entry
                Map<String, List<String>> map = new HashMap<>();
                List<String> list = new ArrayList<>();
                list.add(string);
                map.put(string, list);
                seqs.put(len, map);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (Map<String, List<String>> internalMaps : seqs.values()) {
            for (List<String> seq : internalMaps.values()) {
                res.add(seq);
            }
        }
        return res;
    }

}
