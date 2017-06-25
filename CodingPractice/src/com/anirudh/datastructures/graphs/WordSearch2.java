package com.anirudh.datastructures.graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by paanir on 6/24/17.
 */
/*
212. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
 */
public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        WordSearch ws = new WordSearch();
        for(String word: words){
            if(ws.exist(board, word))
                res.add(word);
        }
        return res;
    }
}
