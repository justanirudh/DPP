package com.anirudh.general_algos.basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paanir on 3/8/17.
 */
/*
151. Reverse Words in a String Add to List

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
 */
public class ReverseWords {

    public static String[] reverseArr(String[] arr) {
        String newArr[] = new String[arr.length];

        int front = 0;
        for (int i = arr.length - 1; i >= 0; --i) {
            newArr[i] = arr[front];
            front++;
        }
        return newArr;
    }

    public static String reverseWords(String s) {
        //trim and split
        String[] words = s.trim().split(" ");
        //filter out multiple spaces
        List<String> result = Arrays.stream(words).filter(word -> !word.equals("")).collect(Collectors.toList());
        words = new String[result.size()];
        words = result.toArray(words);
        //reverse words
        String[] wordsRev = reverseArr(words);
        //join them
        return String.join(" ", wordsRev);
    }


    public static void main(String[] args) {
        System.out.println(reverseWords("   a   b "));
    }
}
