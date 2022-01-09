package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;
import java.util.stream.Collectors;

/*
954. Array of Doubled Pairs
Medium

918

104

Add to List

Share
Given an integer array of even length arr, return true if it is possible to reorder arr such
that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.



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
/*
    Sort the array
    Make a map Integer -> frequency
    every time integer comes, increment frequency
    every time integer*2 comes, decrease frequency and remove from map

    for negative, will need to look for integer/2
 */
public class ArrayofDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        if (arr.length % 2 != 0)
            return false;
        List<Integer> sorted = Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());

        Map<Integer, Integer> state = new HashMap<>(); // number -> frequency
        for (int elem : sorted) {
            int complement = (elem >= 0) ? (elem / 2) : (elem * 2);
            if ((elem >= 0 && elem % 2 != 0) || !state.containsKey(complement)) { //is +ve odd(odd required because 3/2 = 1 which can be in map and give false positive; +ve required because {-6,-3} will give false negative ) or complement not present
                state.put(elem, state.getOrDefault(elem, 0) + 1);
            } else { //is even and a/2 or a*2 is present
                if (state.containsKey(complement)) //has its complement
                    state.put(complement, state.get(complement) - 1);
                if (state.get(complement) == 0) //if got its complement, remove from map, so it doesnt disturb next pair
                    state.remove(complement);
            }
        }
        return state.isEmpty();
    }
}
