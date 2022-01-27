package com.anirudh.companies_21_22.google.lc_last_6m;

/*
2007. Find Original Array From Doubled Array
Medium

308

28

Add to List

Share
An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.



Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.
 */
/*
    Sort the array
    Make a map Integer -> frequency
    every time integer comes, increment frequency
    every time integer*2 comes, decrease frequency and remove from array

    for negative, will need to look for integer/2
 */
import java.util.*;

public class FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0)
            return new int[0];
        List<Integer> positive = new ArrayList<>();
        List<Integer> all = new ArrayList<>(); //negative initially, then merged
        for (int a : changed) {
            if (a < 0)
                all.add(a);
            else {
                positive.add(a);
            }
        }
        if (all.size() % 2 != 0 || positive.size() % 2 != 0)
            return new int[0];

        Collections.sort(positive);
        all.sort(Collections.reverseOrder());
        all.addAll(positive); //merge to same arr; negative rev sorted, postive proper sorted

        Map<Integer, Integer> state = new HashMap<>();
        int[] res = new int[changed.length / 2];
        int i = 0;
        for (int a : all) { //start from low to hi
            if (a % 2 != 0 || !state.containsKey(a / 2)) { //is odd or (is even but a/2 not present)
                state.put(a, state.getOrDefault(a, 0) + 1);
            } else { //is even and a/2 present
                if (state.containsKey(a / 2)) {  //has its complement
                    state.put(a / 2, state.get(a / 2) - 1);
                    res[i] = a / 2;
                    i++;
                }
                if (state.get(a / 2) == 0) //if got all its complements, remove from map, so it doesnt disturb next pair
                    state.remove(a / 2);
            }
        }
        if (!state.isEmpty())
            return new int[0];
        else
            return res;

    }
}
