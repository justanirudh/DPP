package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.StringJoiner;

/*
418. Sentence Screen Fitting
Medium

769

383

Add to List

Share
Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.

The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.



Example 1:

Input: sentence = ["hello","world"], rows = 2, cols = 8
Output: 1
Explanation:
hello---
world---
The character '-' signifies an empty space on the screen.
Example 2:

Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
Output: 2
Explanation:
a-bcd-
e-a---
bcd-e-
The character '-' signifies an empty space on the screen.
Example 3:

Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
Output: 1
Explanation:
i-had
apple
pie-i
had--
The character '-' signifies an empty space on the screen.


Constraints:

1 <= sentence.length <= 100
1 <= sentence[i].length <= 10
sentence[i] consists of lowercase English letters.
1 <= rows, cols <= 2 * 104
 */
/*
Fill current sentence greedily. Use mod to wrap around in the sentence
T: O(n^2)

Better soln (T: O (n)): https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
 */

/*
Option1: TLE: greedily take words until leach row is filled: O(n^2)

Option 2:
Imagine an infinite sentence that are concatenated by words from the given sentence, infiStr. We want to cut the infiStr properly and put a piece at each row of the screen.
We maintain a pointer ptr. The ptr points to a position at infiStr, where next row will start. Cutting the infiStr and putting a piece at a row can be simulated as advancing the pointer by cols positions.
After advancing the pointer, if ptr points to a space, it means the piece can fit in row perfectly. If ptr points to the middle of a word, we must retreat the pointer to the beginning of the word, because a word cannot be split into two lines.
 */
public class SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        String joined = String.join(" ", sentence) + " "; //or use string joiner
        int len = joined.length();
        int idx = 0;
        for (int i = 0; i < rows; ++i) {
            idx += cols;
            if (joined.charAt(idx % len) == ' ') { //fits perfectly in current row
                idx++; //go to start of next word
            } else { //if not a space, in the middle of a word. decrement idx until the start of a word is found
                while (idx > 0 && joined.charAt((idx - 1) % len) != ' ') {
                    idx--;
                }
            }
        }
        return idx / len;
    }

    //Gives TLE
    public int wordsTypingSlow(String[] sentence, int rows, int cols) {
        int res = 0;
        int idx = 0; //use idx % sentence.len
        int len = sentence.length;
        for (int i = 0; i < rows; ++i) {
            int rem = cols;
            while (rem > sentence[idx % len].length()) { //can add word + space
                rem -= sentence[idx % len].length();
                rem -= 1; //space
                if (idx % len == len - 1) //completed a sentence
                    res++;
                idx++;
            }
            if (rem == sentence[idx % len].length()) {  //last word in a row
                if (idx % len == len - 1)
                    res++;
                idx++;
            }
            //if rem < sentence[idx % sentence.length].length(), go to next row
        }
        return res;
    }
}
