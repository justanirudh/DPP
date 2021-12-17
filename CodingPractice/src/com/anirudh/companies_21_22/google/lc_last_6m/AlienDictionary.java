package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;

/*
269. Alien Dictionary
Hard

3139

647

Add to List

Share
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 */
/*
Create graph by comparing each pair of words
Do topological sort by using DFS with cycle detection
 */
public class AlienDictionary {
    Map<Character, List<Character>> graph;
    StringBuilder sb;
    Set<Character> visited;
    Set<Character> stack; //recursion stack
    boolean hasCycle;
    boolean atLeastOneOrder;

    void addToGraph(String w1, String w2) {
        if (w1.equals(w2)) { //just add all and return
            for (int j = 0; j < w2.length(); ++j) {
                if (!graph.containsKey(w2.charAt(j)))
                    graph.put(w2.charAt(j), new ArrayList<>());
            }
            return;
        }

        int minLen = Math.min(w1.length(), w2.length());
        int i = 0;
        for (; i < minLen; ++i) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if (!graph.containsKey(c1)) //add and continue
                graph.put(c1, new ArrayList<>());
            if (c1 != c2) {
                //add edge
                graph.get(c1).add(c2);
                atLeastOneOrder = true;
                break;
            }
        }
        //now just add rest to the graph so that we dont miss any uncompared letterd
        for (int j = i + 1; j < w1.length(); ++j) {
            if (!graph.containsKey(w1.charAt(j)))
                graph.put(w1.charAt(j), new ArrayList<>());
        }

        for (int j = i; j < w2.length(); ++j) { //starting from i as it was not added in break
            if (!graph.containsKey(w2.charAt(j)))
                graph.put(w2.charAt(j), new ArrayList<>());
        }
    }

    void doTopologicalSort(Character c) {
        visited.add(c);
        stack.add(c);
        for (Character neighbour : graph.get(c)) {
            if (stack.contains(neighbour)) {
                hasCycle = true;
                return;
            }
            if (!visited.contains(neighbour)) {
                doTopologicalSort(neighbour);
            }
        }
        sb.insert(0, c);
        stack.remove(c);
    }

    public String alienOrder(String[] words) {
        graph = new HashMap<>();
        sb = new StringBuilder();
        visited = new HashSet<>();
        hasCycle = false;
        atLeastOneOrder = false;

        Set<String> unique = new HashSet<>();

        //CREATE DAG
        unique.add(words[0]);
        for (int i = 0; i < words.length - 1; ++i) {
            unique.add(words[i + 1]);
            addToGraph(words[i], words[i + 1]);
        }

        if (unique.size() == 1) //if just 1 word, return it
            return words[0];

        //DO TOPOLOGICAL SORT
        for (Character c : graph.keySet()) {
            stack = new HashSet<>();
            if (!visited.contains(c)) {
                doTopologicalSort(c);
                if (hasCycle)
                    return "";
            }
        }

        if (!atLeastOneOrder)
            return "";
        else
            return sb.toString();

    }
}
