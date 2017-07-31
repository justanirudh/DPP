package com.anirudh.datastructures.graphs;

import com.anirudh.general_algos.basics.QueueLLJava;

import java.util.*;
//BFS

/**
 * Created by paanir on 1/11/17.
 */
/*
127. Word Ladder
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

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
public class WordLadder {

    private static class Node {
        String s;
        String parentString = "";
        String color = "white";
        Integer distSrc = -1;
        ArrayList<String> neighbors = new ArrayList<>();

        public Node(String s) {
            this.s = s;
        }
    }

    private static boolean isDiffOne(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int diff = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i))
                ++diff;
            if (diff > 1)
                return false;
        }
        if (diff == 1)
            return true;
        else
            return false;
    }

    //undirected graph
    private static HashMap<String, Node> createGraph(Set<String> wordList) {
        HashMap<String, Node> graph = new HashMap<>();
        for (String s : wordList) {
            if (!graph.containsKey(s))
                graph.put(s, new Node(s));
            for (String key : graph.keySet()) {
                if (isDiffOne(s, key)) {
                    graph.get(s).neighbors.add(key);
                    graph.get(key).neighbors.add(s);
                }
            }
        }
        return graph;
    }

//    public static HashMap<String, Node> doBFS(HashMap<String, Node> graph, String source) {
//        Node srcNode = graph.get(source);
//        srcNode.color = "gray"; //discovered
//        srcNode.distSrc = 0;
//        QueueLLJava queue = new QueueLLJava<String>();
//        queue.enqueue(source);
//        while (!queue.isEmpty()) {
//            String curr = ((String) queue.dequeue());
//            for (String neigh : graph.get(curr).neighbors) {
//                if (graph.get(neigh).color.equals("white")) { //undiscovered
//                    Node neighNode = graph.get(neigh);
//                    neighNode.color = "gray";
//                    neighNode.distSrc = graph.get(curr).distSrc + 1;
//                    neighNode.parentString = curr;
//                    queue.enqueue(neigh);
//                }
//            }
//            graph.get(curr).color = "black"; //finished
//        }
//        return graph;
//    }

    public static int doBFSAndGetDist(HashMap<String, Node> graph, String source, String dest) {
        Node srcNode = graph.get(source);
        srcNode.color = "gray"; //discovered
        srcNode.distSrc = 0;
        QueueLLJava queue = new QueueLLJava<String>();
        queue.enqueue(source);
        while (!queue.isEmpty()) {
            String curr = ((String) queue.dequeue());
            for (String neigh : graph.get(curr).neighbors) {
                if (graph.get(neigh).color.equals("white")) { //undiscovered
                    Node neighNode = graph.get(neigh);
                    neighNode.color = "gray";
                    neighNode.distSrc = graph.get(curr).distSrc + 1;
                    if (neigh.equals(dest))
                        return neighNode.distSrc + 1;
                    neighNode.parentString = curr;
                    queue.enqueue(neigh);
                }
            }
            graph.get(curr).color = "black"; //finished
        }
        return 0;
    }

    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        //create graph, do BFS
        wordList.add(beginWord);
        wordList.add(endWord);

        //create graph
        HashMap<String, Node> graph = createGraph(wordList);

        HashMap<String, Node> graphPreBFS = new HashMap<>(graph);

        return doBFSAndGetDist(graphPreBFS, beginWord, endWord);
    }

    //Method 2
    private static class Word {
        String s;
        Integer dist;

        public Word(String s, Integer dist) {
            this.s = s;
            this.dist = dist;
        }
    }

    public static int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);
        Queue<Word> q = new LinkedList<>();
        q.add(new Word(beginWord, 1));
        while (!q.isEmpty()) {
            Word wCurr = q.remove();
            String curr = wCurr.s;
            HashSet<String> removeThese = new HashSet<>();
            for (String str : wordList) {
                if (isDiffOne(curr, str)) {
                    if (str.equals(endWord))
                        return wCurr.dist + 1;
                    Word newW = new Word(str, wCurr.dist + 1);
                    q.add(newW);
                    removeThese.add(str);
                }
            }
            wordList.removeAll(removeThese);
        }
        return 0;
    }

    public static void main(String[] args) {
//        String beginWord = "hit";
//        String endWord = "cog";
//        Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));

        String beginWord = "nanny";
        String endWord = "aloud";
//        System.out.println(ladderLength2(beginWord, endWord, wordList));
    }

}
