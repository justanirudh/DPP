package com.anirudh.subarray_substring.string_manipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by paanir on 5/5/17.
 */
/*
187. Repeated DNA Sequences
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */
public class FindRepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> hs = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; ++i) {
            String sub = s.substring(i, i + 10);
            boolean added = hs.add(sub);
            if (!added) //add returns false if sub already present
                res.add(sub); //also needs to be hashset so that if >= 3 repetitions of a dna segment, it only counts as 1
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        FindRepeatedDNASequences foo = new FindRepeatedDNASequences();
        List<String> list = foo.findRepeatedDnaSequences("AAAAAAAAAAAA");
        System.out.println(Arrays.toString(list.toArray()));
    }
}
