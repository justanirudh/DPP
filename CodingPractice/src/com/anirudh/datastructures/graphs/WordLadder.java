package com.anirudh.datastructures.graphs;

import com.anirudh.general_algos.basics.QueueLLJava;

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
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 */

//Destructive BFS, like ReconstructItinierary
public class WordLadder {

    private static boolean isDiffOne(String word1, String word2) {
        int diffs = 0;
        for (int i = 0; i < word1.length(); ++i) {
            if (word1.charAt(i) != word2.charAt(i))
                diffs++;
            if (diffs > 1)
                return false;
        }
        return true;
    }

    private static class Word {
        String s;
        Integer dist;

        Word(String s, Integer dist) {
            this.s = s;
            this.dist = dist;
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wL) {
        Set<String> wordList = new HashSet<>(wL);
        if (!wordList.contains(endWord))
            return 0;

        Deque<Word> q = new ArrayDeque<>();
        q.offer(new Word(beginWord, 1));

        while (!q.isEmpty()) {
            Word wCurr = q.poll();
            String curr = wCurr.s;
            Set<String> removeThese = new HashSet<>();
            for (String str : wordList) {
                if (isDiffOne(curr, str)) {
                    if (str.equals(endWord))
                        return wCurr.dist + 1;
                    Word newW = new Word(str, wCurr.dist + 1);
                    //transfer words from undiscovered wordList to discovered Word List
                    q.offer(newW);
                    removeThese.add(str); //cant remove from wordList here: concurrentmodificationexception
                }
            }
            wordList.removeAll(removeThese); //equivalent to being visited
        }
        return 0; //no such transformation
    }

    //---------------------------------------------------------------------------------------------Method 2

    private int bfs(List<String> wordList, boolean[] discovered, List<List<Integer>> neighboursList, int[] distances, int beginIndex, String endWord) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(beginIndex);
        distances[beginIndex] = 1;
        discovered[beginIndex] = true;
        while (!q.isEmpty()) {
            int wordIndex = q.poll();
            int wordDistance = distances[wordIndex];
            if (wordList.get(wordIndex).equals(endWord))
                return wordDistance;
            List<Integer> neighbours = neighboursList.get(wordIndex);
            for (int neighbour : neighbours) {
                if (!discovered[neighbour]) {
                    distances[neighbour] = wordDistance + 1;
                    discovered[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
        return 0;
    }

    private List<Integer> getNeighboursOf(String word, List<String> wordList) {
        List<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < wordList.size(); ++i) {
            String w = wordList.get(i);
            if (!w.equals(word) && isDiffOne(w, word))
                neighbours.add(i);
        }
        return neighbours;
    }

    public int ladderLengthLong(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        int beginIndex;
        int wordsSize = wordList.size();
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
            wordsSize++;
            beginIndex = wordsSize - 1;
        } else {
            beginIndex = wordList.indexOf(beginWord);
        }

        //1. create a graph of the wordList
        /*
            discovered array: whether a word has been discovered already or not
            array of neighbours of each word
        */
        boolean[] discovered = new boolean[wordsSize];
        int[] distances = new int[wordsSize];
        List<List<Integer>> neighbours = new ArrayList<>(); //array of array of indices
        for (int i = 0; i < wordsSize; ++i) {
            neighbours.add(i, getNeighboursOf(wordList.get(i), wordList));
        }
        //2. BFS with beginWord the source
        return bfs(wordList, discovered, neighbours, distances, beginIndex, endWord);
    }




    //----------------------------------------------------------------

}
