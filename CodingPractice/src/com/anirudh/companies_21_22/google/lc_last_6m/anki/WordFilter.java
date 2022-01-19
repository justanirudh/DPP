package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.Arrays;
import java.util.List;

/*
745. Prefix and Suffix Search
Hard

939

318

Add to List

Share
Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.


Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".


Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
Accepted
46,672
Submissions
129,124
 */

/*
Trie of Suffix Wrapped Words
For apple,put in #apple, e#apple, le#apple, ple#apple, pple#apple,apple#apple
For a prefix,suffix query, look for "suffix#prefix" in the trie
eg. for {ap, le}, look for le#ap

For each node,also put in index
class TrieNode{
    TrieNode[] children;
    int index;
}
iterate from 0 to max_words in the words list
replace weights as you go
naturally each weight would then be the maximum
----------------------------------------------
TLE:
1. Create 2 tries: 1 for all words T and 1 for all reversed words T'
2. find prefix in T and collect all word's indices in a tree-set
3. find suffix-reversed in T' and collect all word's indices in a tree-set
4. intersect t and T'
5. get the max elem by intersected.lastKey();
---------------------------------------------
Brute-force:use startsWith and endsWith for each word
 */
public class WordFilter {
    //TLE
    List<String> wordsL;

    public WordFilter(String[] words) {
        wordsL = Arrays.asList(words);
    }

    public int f(String prefix, String suffix) {
        for (int i = wordsL.size() - 1; i >= 0; --i) {
            if (wordsL.get(i).startsWith(prefix) && wordsL.get(i).endsWith(suffix))
                return i;
        }
        return -1;
    }
}
