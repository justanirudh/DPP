package com.anirudh.interview_prep_2021_2022.two_sigma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The sentence “The quick brown fox jumps over the lazy dog” contains every single letter in the alphabet.
Such sentences are called pangrams. You are to write a function getMissingLetters,
which takes a String, sentence, and returns all the letters it is missing
(which prevent it from being a pangram). You should ignore the case of the letters in sentence,
and your return should be all lower case letters, in alphabetical order. '
You should also ignore all non US-ASCII characters.
 */

/*
iterate over sentence, put in set
iterate over alphabet, check if in set or not
 */
public class MissingLetters {

    public List<Character> getMissingLetters(String sentence) {
        Set<Character> senSet = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            senSet.add(Character.toLowerCase(c));
        }
        List<Character> res = new ArrayList<>();
        //String alphabet = "abcdefghijklmnopqrstuvwxyz"; or iterate through this
        for (char alpha = 'a'; alpha <= 'z'; ++alpha) {
            if (!senSet.contains(alpha))
                res.add(alpha);
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "The quik brown ox jumps oer the lay do";
        new MissingLetters().getMissingLetters(str).forEach(x -> System.out.print(x + " "));
    }
}
