package com.anirudh.companies_21_22.google.lc_last_6m.anki;

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
        all.addAll(positive); //merge to same arr

        Map<Integer, Integer> state = new HashMap<>();
        int[] res = new int[changed.length / 2];
        int i = 0;
        for (int a : all) {
            if (a % 2 != 0 || !state.containsKey(a / 2)) { //is odd or (is even but a/2 not present)
                state.put(a, state.getOrDefault(a, 0) + 1);
            } else { //is even and a/2 present
                if (state.containsKey(a / 2)) {  //has its complement
                    state.put(a / 2, state.get(a / 2) - 1);
                    res[i] = a / 2;
                    i++;
                }
                if (state.get(a / 2) == 0) //if got its complement, remove from map, so it doesnt disturb next pair
                    state.remove(a / 2);
            }
        }
        if (!state.isEmpty())
            return new int[0];
        else
            return res;

    }
}
