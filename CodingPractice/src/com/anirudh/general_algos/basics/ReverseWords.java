package com.anirudh.general_algos.basics;

import java.util.Arrays;

/**
 * Created by paanir on 3/8/17.
 */
public class ReverseWords {

    public String[] reverseArr(String[] arr){
        String newArr[] = new String[arr.length];

        int front = 0;
        for(int i = arr.length - 1; i >= 0; --i){
            newArr[i] = arr[front];
            front++;
        }
        return newArr;
    }

    public String reverseWords(String s) {
        String trimmedS = s.trim();
        String[] words = trimmedS.split(" ");
        words = Arrays.stream(words).filter(x -> x != " ").toArray();
        String[] wordsRev = reverseArr(words);
        return String.join(" ", wordsRev);
    }
}
