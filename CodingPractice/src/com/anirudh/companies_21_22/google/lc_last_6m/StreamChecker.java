package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.ArrayList;
import java.util.List;

/*
1032. Stream of Characters
Hard

1428

164

Add to List

Share
Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.

For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.

Implement the StreamChecker class:

StreamChecker(String[] words) Initializes the object with the strings array words.
boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.


Example 1:

Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist


Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
words[i] consists of lowercase English letters.
letter is a lowercase English letter.
At most 4 * 104 calls will be made to query.
Accepted
70,091
Submissions
136,822
 */

/*
Use Trie
find suffix for words == finding prefix for reversed words
Make trie of reversed words
For every new character, traverse reverse of stream
If at any point a word is met, return true; else return false
 */
public class StreamChecker {

    class TrieNode {
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    TrieNode root;
    List<Character> stream;

    public StreamChecker(String[] words) {
        stream = new ArrayList<>();

        //create Trie with reversed words
        root = new TrieNode();
        for (String word : words) {
            String revWord = new StringBuilder(word).reverse().toString();
            TrieNode curr = root;
            for (char c : revWord.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.word = revWord;
        }
    }

    public boolean query(char letter) {
        stream.add(letter);
        TrieNode curr = root;
        for (int i = stream.size() - 1; i >= 0; --i) { //go reverse
            char c = stream.get(i);
            if (curr.children[c - 'a'] != null) {
                if (curr.word != null) //check if found a word
                    return true;
                else
                    curr = curr.children[c - 'a']; // else go to next word
            } else { //no next trienode
                return curr.word != null; //should be a valid leaf
            }
        }
        return curr.word != null; //if word == stream
    }

//Gives TLE

//    String[] words;
//    StringBuilder sb;
//    public StreamChecker(String[] words) {
//        this.words = words;
//        sb = new StringBuilder();
//    }
//
//    public boolean query(char letter) {
//        sb.append(letter);
//        String s = sb.toString();
//        for(String word : words) {
//            if(s.endsWith(word))
//                return true;
//        }
//        return false;
//    }
}
