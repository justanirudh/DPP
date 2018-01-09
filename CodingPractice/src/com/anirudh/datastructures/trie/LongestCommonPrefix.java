package com.anirudh.datastructures.trie;

/**
 * Created by paanir on 1/8/18.
 */
/*
14. Longest Common Prefix
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    //Trie looks like this, in brackets is the value of word: null(null) -> b(null) -> a(null) -> t(bat)
    //beats 23%

    class TrieNode {
        TrieNode[] children;
        String word;
        int size; //number of children of a node

        TrieNode() {
            children = new TrieNode[26];
        }
    }

    void addToTrie(TrieNode curr, String str) {
        char[] arr = str.toCharArray();
        for (char ch : arr) {
            if (curr.children[ch - 'a'] == null) {
                TrieNode tn = new TrieNode();
                curr.children[ch - 'a'] = tn;
                curr.size++;
            }
            curr = curr.children[ch - 'a'];
        }
        curr.word = str;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        TrieNode root = new TrieNode();
        for (String str : strs) {
            addToTrie(root, str);
        }
        //Traverse the trie and stop when either:
        // 1. size of children array is > 1 (means bifurcation) OR
        // 2. a word is reached
        TrieNode curr = root;
        int idx = 0;
        String str = strs[0]; //any word would do
        char[] chars = str.toCharArray(); //any string
        while (curr.word == null && curr.size == 1) {
            curr = curr.children[chars[idx] - 'a'];
            idx++;
        }
        return str.substring(0, idx);
    }
}
