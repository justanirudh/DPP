package com.anirudh.general_algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paanir on 1/25/17.
 */
/*
336. Palindrome Pairs
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of
the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

 */
public class PalindromePairs {

    //Time: O(n^3): nC2 * 2 * O(n) [num combinations total * ways to combine 2 strings * checking if the resulting string is a palindrome]
    public List<List<Integer>> palindromePairsNaive(String[] words) {

        String s = "wweee";
        for (char c : s.toCharArray()) {
            System.out.println();
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < words.length - 1; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                String w1 = words[i];
                String w2 = words[j];
                String try1 = w1 + w2;
                if (try1.equals(new StringBuilder(try1).reverse().toString())) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    list.add(l);
                }
                String try2 = w2 + w1;
                if (try2.equals(new StringBuilder(try2).reverse().toString())) {
                    List<Integer> l = new ArrayList<>();
                    l.add(j);
                    l.add(i);
                    list.add(l);
                }
            }
        }
        return list;
    }

    //---------------------------------------------------------------------------
    //Time: O(nk), where k is the max length of a word!
    public List<List<Integer>> palindromePairs(String[] words) {
        /*
        1. check if a word itself is a palindrom. then check if a null string exists
        2. check if the reverse of the word exists.
        3. break the word at each index.
        33. check if left part is palin. if so, check if reverse of right part is in array. if so, we got 2 words
        33. do same by first checking if right part is palin
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i); //SOOOOOOOOOOO clever :*
        }

        for (int i = 0; i < words.length; i++) {
            String s = words[i];

            //if the word is a palindrome, get index of ""
            if (isPalindrome(s)) {
                if (map.containsKey("")) { //if null string found, then the two strings can be a palindrome
                    if (map.get("") != i) {// not itself
                        //add both combinations
                        ArrayList<Integer> l = new ArrayList<Integer>();
                        l.add(i);
                        l.add(map.get(""));
                        result.add(l);

                        l = new ArrayList<Integer>();

                        l.add(map.get(""));
                        l.add(i);
                        result.add(l);
                    }

                }
            }

            //if the reversed word exists, it is a palindrome
            String reversed = new StringBuilder(s).reverse().toString();
            if (map.containsKey(reversed)) {
                if (map.get(reversed) != i) {
                    ArrayList<Integer> l = new ArrayList<Integer>();
                    l.add(i);
                    l.add(map.get(reversed));
                    result.add(l); // not adding twice as it is looping around and wil get to the reversed word and get added
                }
            }

            for (int k = 1; k < s.length(); k++) {
                String left = s.substring(0, k); //breaking into 2 parts
                String right = s.substring(k);

                //if left part is palindrome, find reversed right part
                if (isPalindrome(left)) { // s: palin + notpalin
                    String reversedRight = new StringBuilder(right).reverse().toString(); //nilapton
                    if (map.containsKey(reversedRight)) {
                        if (map.get(reversedRight) != i) {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(map.get(reversedRight));
                            l.add(i);
                            result.add(l); //nilapton + palin + notpalin
                        }
                    }
                }

                //if right part is a palindrome, find reversed left part
                if (isPalindrome(right)) {
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(reversedLeft)) {
                        if (map.get(reversedLeft) != i) {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(i);
                            l.add(map.get(reversedLeft));
                            result.add(l);
                        }
                    }
                }
            }

        }

        return result;
    }

    public boolean isPalindrome(String s) {


        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }
        return true;
    }
}
