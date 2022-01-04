package com.anirudh.companies_21_22.TTD.anki;

/*
Counting sort (sort a string and return)
also another way: https://www.youtube.com/watch?v=OKd534EWcdk
* */
public class CountingSort {

    static String countingSort(String str) {
        int[] counts = new int[26];
        for(char c : str.toCharArray()) {
            counts[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < counts.length; ++i) {
            int count = counts[i];
            for(int j = 0; j < count; ++j) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String foo = "kajsfkajsbdaghjbfkdjf";
        String res = countingSort(foo);
        System.out.println(res);
    }
}
