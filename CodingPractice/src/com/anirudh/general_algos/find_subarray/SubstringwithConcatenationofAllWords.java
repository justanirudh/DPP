package com.anirudh.general_algos.find_subarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 12/20/16.
 */
/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarmanbarfoo"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).


 */

//TODO: some test cases failing
public class SubstringwithConcatenationofAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {

        HashMap<String, Integer> masterMap = new HashMap<>();
        for (String w : words) {
            if(masterMap.containsKey(w))
                masterMap.put(w, masterMap.get(w) + 1);
            else
                masterMap.put(w, 1);
        }

        int lengthWord = words[0].length();

        ArrayList<Integer> indices = new ArrayList<>();
        int i = 0;
        while (i <= s.length() - (lengthWord * words.length)) {
            if (masterMap.containsKey(s.substring(i, i + lengthWord))) { //first word is in array
                int end = i + (lengthWord * words.length);
                System.out.println("i = " + i);
                System.out.println("end = " + end);
                HashMap<String, Integer> slaveMap = new HashMap<>(masterMap); //making copy
                int j;
                int firstrepitition = -1;
                for (j = i; j < end; j = j + lengthWord) {
                    String word = s.substring(j, j + lengthWord);
                    System.out.println("word = " + word);
                    if (slaveMap.containsKey(word) && slaveMap.get(word) != 0) {//contains and unexplored
                        if(masterMap.get(word) - slaveMap.get(word) == 1) // first repitition
                            firstrepitition = j;
                        slaveMap.put(word, slaveMap.get(word) - 1);
                    }
                    else if (slaveMap.containsKey(word) && slaveMap.get(word) == 0) { //contains and explored
                        if(firstrepitition == -1)
                            i = j;
                        else
                            i = firstrepitition;
                        break;
                    } else { // j to j + lengthWord is not a 'valid' word
                        i = j + 1;
                        break;
                    }
                }
                System.out.println("first repition = " + firstrepitition);
                System.out.println("j = " + j);
                if (j >= end) { //no breaks
                    indices.add(i);
                    i = i + lengthWord; //just ignoring first word as there can be overlapping words
                }
                System.out.println("--------------");
            } else {
                i++;
                System.out.println("word not present");
            }
        }
        return indices;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaa";
        String[] words = {"aa", "aa", "aa"}; //0,1,2
        List<Integer> list = findSubstring(s, words);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
