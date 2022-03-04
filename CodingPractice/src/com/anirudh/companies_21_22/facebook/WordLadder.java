package com.anirudh.companies_21_22.facebook;

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

/*
 * BFS to find the shortest distance
 * All words of same length
 * 1. Create a patternMap of pattern -> words matching the pattern
 * 1.1 For each word, put asterix in each position (use substring)
 * 1.2 put that as key in the map and the list of words as values matching the pattern
 * 1.3 This is our adjacency list
 * 2. Create a queue with <beginWord, dist =1> offerred.
 * 2.1 Create a visited hashset. Put both words and patterns as visited in it.
 * 3. Do BFS
 * 3.1 Pull from queue
 * 3.2 Create patterns by placing asterisk in each position.
 * 3.3 if the pattern has been visited, move to next pattern
 * 3.4 If the pattern is not visited, go to arraylist of words that have pattern as key in the patternMap
 * 3.5 If any of them is endWord, increment dist and return
 * 3.6 If the word is not visited, put in visited set, and offer in queue
 * 3.7 after a pattern is done, mark the pattern as visited as well
 */

public class WordLadder {

    class Pair {
        String word;
        int dist;

        Pair(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int wordLen = beginWord.length(); //all words of same len

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> patternToMatches = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < wordLen; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String pattern = word.substring(0, i) + '*' + word.substring(i + 1);
                        patternToMatches.putIfAbsent(pattern, new ArrayList<>());
                        patternToMatches.get(pattern).add(word);
                    }
                });

        //BFS
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(beginWord, 1));
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Pair node = queue.remove();
            String word = node.word;
            int level = node.dist;
            for (int i = 0; i < wordLen; i++) { //go through each pattern that can be formed from the word

                // Intermediate words for current word
                String currPattern = word.substring(0, i) + '*' + word.substring(i + 1);
                if (visited.contains(currPattern))
                    continue;

                // Next states are all the words which share the same intermediate state.
                List<String> neighbours = patternToMatches.getOrDefault(currPattern, new ArrayList<>());
                for (String adjacentWord : neighbours) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.contains(adjacentWord)) {
                        visited.add(adjacentWord);
                        queue.add(new Pair(adjacentWord, level + 1));
                    }
                }
                visited.add(currPattern); //also adding current pattern
            }
        }
        return 0;
    }

}
