//package com.anirudh.general_algos;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.TreeSet;
//
///**
// * Created by paanir on 11/21/17.
// */
//public class LongestWordInDictionary {
//
//    static class Comp implements Comparator<String> {
//        public int compare(String o1, String o2) {
//            return Integer.compare(o1.length(), o2.length());
//        }
//    }
//    public String longestWord(String[] words) {
//        /*
//        1.sort by length
//        */
//        Arrays.sort(words, new Comp());
//        for(int i = words.length)
//
//    }
//
//    public static void main(String[] args) {
//        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        Arrays.sort(words, new Comp());
//
//
//        TreeSet<String> wordsSet = new TreeSet<>();
//        for(String word : words)
//            System.out.print(word + " ");
//
////        for(String word : wordsSet)
////            System.out.print(word + " ");
//    }
//}
