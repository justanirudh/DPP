package com.anirudh.datastructures.trie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paanir on 1/8/18.
 */
/*
14. Longest Common Prefix
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Write a function to find the longest common prefix string amongst an array of strings.
 */
/*
O(N) time, O(N) space
 */
public class LongestCommonPrefix {

    //Trie looks like this, in brackets is the value of word: null(null) -> b(null) -> a(null) -> t(bat)
    //beats 23%

    class TrieNode {
        TrieNode[] children;
        String word;
        int numChildren; //number of children of a node

        TrieNode() {
            children = new TrieNode[26];
        }
    }

    void addToTrie(TrieNode curr, String str) {
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            int index = ch - 'a';
            if (curr.children[index] == null) {
                TrieNode tn = new TrieNode();
                curr.children[index] = tn;
                curr.numChildren++;
            }
            curr = curr.children[index];
        }
        curr.word = str;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        TrieNode root = new TrieNode(); //first node is dummy node
        for (String str : strs) {
            addToTrie(root, str);
        }
        //Traverse the trie and stop when either:
        // 1. size of children array is > 1 (means bifurcation) OR
        // 2. a word is reached
        TrieNode curr = root;
        int idx = 0;
        String str = strs[0]; //any word would do
        char[] chars = str.toCharArray();
        while (curr.word == null && curr.numChildren == 1) {
            curr = curr.children[chars[idx] - 'a'];
            idx++;
        }
        return str.substring(0, idx);
    }

    // faster than 33.26%
    public String longestCommonPrefix2(String[] strs) {
        if(strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < minLen)
                minLen = str.length();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLen; ++i) {
            Set<Character> singleton = new HashSet<>();
            for (String str : strs) {
                Character ch = str.charAt(i);
                singleton.add(ch);
                if (singleton.size() > 1)
                    break;
            }
            if (singleton.size() > 1)
                break;
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }

    // faster than 65.39%
    String getCommonSubstring(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        for(int i = 0; i < Math.min(s1.length(), s2.length()); ++i) {
            if(s1c[i] == s2c[i])
                sb.append(s1c[i]);
            else
                break;
        }
        return sb.toString();
    }

    public String longestCommonPrefix3(String[] strs) {
        String res = strs[0];
        for(int i = 1; i < strs.length; ++i) {
            String next = strs[i];
            res = getCommonSubstring(res, next);
        }
        return res;
    }
}
