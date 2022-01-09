package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.ArrayList;
import java.util.List;

/*
68. Text Justification
Hard

1456

2537

Add to List

Share
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 */
/*
Use 2 pointers left and right to get each line's start and end
For each line
    first find the number of words in it
        greedily get words based on their (length + 1 (for space b/w words))
    then justify it
        calculate:
            total spaces to be distributed
            total gaps b/w words
            find spaces per gap
            remainder spaces to be distributed evenly starting from left
        distribute spaces per gap and remainder across all words
        add extra spaces at the end

 */
public class TextJustification {
    String[] words;
    int maxWidth;

    String createSpace(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(" ");
            num--;
        }
        return sb.toString();
    }

    String addSpacesAtTheEnd(String s) {
        int numSpaces = maxWidth - s.length();
        return s + createSpace(numSpaces);
    }

    int sumLetters(int left, int right) {
        int sum = 0;
        while (left <= right) {
            sum += words[left].length();
            left++;
        }
        return sum;
    }

    String justify(int left, int right) {
        //if only 1 word, just add spaces at the end
        if (left == right)
            return addSpacesAtTheEnd(words[left]);

        int numTotalSpaces = maxWidth - sumLetters(left, right);
        int numGapsBetweenWords = right - left;
        boolean isLastLine = (right == words.length - 1);

        String spacesBetweenEachWord = isLastLine ? " " : createSpace(numTotalSpaces / numGapsBetweenWords);
        int numRemSpaceToBeDistributed = isLastLine ? 0 : numTotalSpaces % numGapsBetweenWords;

        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; ++i) {
            sb.append(words[i]);
            sb.append(spacesBetweenEachWord);
            if (numRemSpaceToBeDistributed != 0) { //add extra remainders from left to right
                sb.append(" ");
                numRemSpaceToBeDistributed--;
            }
        }

        return addSpacesAtTheEnd(sb.toString().trim());
    }

    int getWordsForLine(int left) {
        int right = left;
        int len = words[right].length(); // no space added
        while (len <= maxWidth && right < words.length - 1) {
            right++;
            len += 1 + words[right].length(); // 1 space + word
        }
        if (len > maxWidth) // but if right has reached end of all strings (right >= words.length - 1), dont decrement right
            right--;
        return right;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        this.words = words;
        this.maxWidth = maxWidth;

        List<String> res = new ArrayList<>();
        int l = 0;
        while (l < words.length) {
            int r = getWordsForLine(l);
            String justified = justify(l, r); //both inclusive
            res.add(justified);
            l = r + 1;
        }
        return res;
    }
}
