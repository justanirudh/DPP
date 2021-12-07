package com.anirudh.companies_21_22.two_sigma;

import java.util.*;
/*
Sort a list of words and integers, but integers should all remain in their original indices,
and words should remain in their original indices.
 */
/*
Create a function to check if a string is an integer or not
Option 1: if Integer.parseInt throws, it is not
Option 2:
    if (starts with -)
        total length > 1, else false
    for i= 1 -> len
        if(char[i] < '0' || > '9') false

Use a hashset to record integer indices
 */
public class SortListOfWordAndIntegers {

    boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    void sortIntegerAndWords(String[] arr) {
        List<String> words = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        Set<Integer> integerIndices = new HashSet<>();
        for(int i = 0; i < arr.length; ++i) {
            String s = arr[i];
            if(isInteger(s)) {
                integers.add(Integer.parseInt(s));
                integerIndices.add(i);
            }
            else
                words.add(s);
        }
        Collections.sort(words);
        Collections.sort(integers);

        Iterator<String> wIter = words.iterator();
        Iterator<Integer> iIter = integers.iterator();
        for(int i = 0; i < arr.length; ++i) {
            if(integerIndices.contains(i))
                arr[i] = Integer.toString(iIter.next());
            else
                arr[i] = wIter.next();
        }
    }

    public static void main(String[] args) {
        String[] str = {"2", "charlie", "5", "alpha", "1", "echo", "delta", "4", "beta", "3", "-1", "foxtrot", "-5"};
        new SortListOfWordAndIntegers().sortIntegerAndWords(str);
        for(String s: str) {
            System.out.print(s + " ");
        }
    }
}
