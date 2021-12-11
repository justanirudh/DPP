package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.*;
import java.util.stream.Collectors;

/*
954. Array of Doubled Pairs
Medium

918

104

Add to List

Share
Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.



Example 1:

Input: arr = [3,1,3,6]
Output: false
Example 2:

Input: arr = [2,1,2,6]
Output: false
Example 3:

Input: arr = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: arr = [1,2,4,16,8,4]
Output: false
 */
public class ArrayofDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        if (arr.length % 2 != 0)
            return false;

        List<Integer> positive = new ArrayList<>();
        List<Integer> all = new ArrayList<>(); //negative initially, then merged
        for (int a : arr) {
            if (a < 0)
                all.add(a);
            else {
                positive.add(a);
            }
        }
        if (all.size() % 2 != 0 || positive.size() % 2 != 0)
            return false;

        Collections.sort(positive);
        all.sort(Collections.reverseOrder());
        all.addAll(positive); //merge to same arr

        Map<Integer, Integer> state = new HashMap<>();
        for (int a : all) {
            if (a % 2 != 0 || !state.containsKey(a / 2)) { //is odd or (is even but a/2 not present)
                state.put(a, state.getOrDefault(a, 0) + 1);
            } else { //is even and a/2 present
                if (state.containsKey(a / 2)) //has its complement
                    state.put(a / 2, state.get(a / 2) - 1);
                if (state.get(a / 2) == 0) //if got its complement, remove from map, so it doesnt disturb next pair
                    state.remove(a / 2);
            }
        }
        return state.isEmpty();
    }
}
