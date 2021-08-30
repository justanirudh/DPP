package com.anirudh.interview_prep_2021.facebook;

import java.util.*;
//BFS

/**
 * Created by paanir on 1/11/17.
 */
/*
127. Word Ladder
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 */

/**
 * BFS to find shortest distance
 */

public class WordLadder {
    Map<String, List<String>> graph = new HashMap<>();
    Set<String> visited = new HashSet<>();

    class Node {
        String word;
        int dist;

        Node(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    int doBFS(String start, String end) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 1));
        visited.add(start);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.word.equals(end))
                return curr.dist;
            for (String neighbour : graph.get(curr.word)) {
                if (!visited.contains(neighbour)) {
                    queue.offer(new Node(neighbour, curr.dist + 1));
                    visited.add(neighbour); //visited needs to be here so that nodes are not pushed twice
                }
            }
        }
        return 0;
    }

    boolean isDiffOne(String a, String b) {
        if (a.length() != b.length() || a.equals(b))
            return false;
        int diff = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                if (diff == 1)
                    return false;
                diff++;
            }
        }
        return true;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        //create graph
        wordList.add(beginWord);
        for (int i = 0; i < wordList.size(); ++i) {
            String src = wordList.get(i);
            if (!graph.containsKey(src))
                graph.put(src, new ArrayList<>());
            for (int j = i + 1; j < wordList.size(); ++j) {
                String curr = wordList.get(j);
                if (isDiffOne(src, curr)) {
                    graph.get(src).add(curr);
                    if (!graph.containsKey(curr))
                        graph.put(curr, new ArrayList<>());
                    graph.get(curr).add(src);
                }
            }
        }

        return doBFS(beginWord, endWord);

    }

}
