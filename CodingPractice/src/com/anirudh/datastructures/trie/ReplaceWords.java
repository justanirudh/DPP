package com.anirudh.datastructures.trie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by paanir on 12/27/17.
 */
/*
648. Replace Words
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
In English, we have a concept called root, which can be followed by some other words to form another longer word -
let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence
with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000
 */
public class ReplaceWords {

    //D words of d len each, Sentence with s letters

    //O(Dd + s)
    //create trie //beats 77%

    class TrieNode {
        String word; //for leaf
        TrieNode[] children; //for its children

        TrieNode() {
            children = new TrieNode[26]; //each node can have 26 children max for each letter
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        //create Trie
        TrieNode trieRoot = new TrieNode();
        //add all words to the same trie
        for (String word : dict) {
            //add to Trie
            TrieNode curr = trieRoot;
            char[] rootArr = word.toCharArray();
            for (char ch : rootArr) {
                int index = ch - 'a';
                if (curr.children[index] == null)
                    curr.children[index] = new TrieNode();
                curr = curr.children[index]; //bring pointer down to child node
            }
            curr.word = word; //add entire word to leaf
        }

        String[] words = sentence.split(" ");

        StringJoiner sj = new StringJoiner(" ");
        //get roots for each word in sentence by using the trie
        for (String word : words) {
            char[] wordArr = word.toCharArray();
            TrieNode curr = trieRoot;
            for (char ch : wordArr) {
                int idx = ch - 'a';
                if (curr.children[idx] == null || curr.word != null) //reached a node that doesnt have the correct child or reached a leaf
                    break;
                curr = curr.children[idx];
            }
            if (curr.word != null) //if reached a leaf, then got the smallest root word (as breaks in first discovery of word)
                sj.add(curr.word);
            //else keep the word as it is
        }
        return sj.toString();
    }


    //--------------------------------------------------------

    //O(D + s) ~ O(n^2) //beats 33%
    public String replaceWords2(List<String> dict, String sentence) {
        Set<String> roots = new HashSet<>(dict);
        String[] words = sentence.split(" ");
        for (int j = 0; j < words.length; ++j) {
            //get all prefixes
            String word = words[j];
            for (int i = 1; i <= word.length(); ++i) {
                String sub = word.substring(0, i);
                if (roots.contains(sub)) {
                    words[j] = sub;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    //-------------------------------------------------------------------------------

    //O(Ds) // beats 61%
    public String replaceWords1(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        for (String root : dict) {
            for (int i = 0; i < words.length; ++i) {
                if (words[i].startsWith(root))
                    words[i] = root;
            }
        }
        return String.join(" ", words);
    }


}
