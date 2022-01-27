package com.anirudh.companies_21_22.google.lc_last_6m;

import java.util.*;

/**
 * Created by paanir on 9/2/21.
 */
/*
843. Guess the Word
Hard

941

1010

Add to List

Share
This is an interactive problem.

You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.

You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.



Example 1:

Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
Output: You guessed the secret word correctly.
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Example 2:

Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
Output: You guessed the secret word correctly.
 */

/*
Remember we have access to the wordlist!!
[Accepted] Option 1: Pick a random word 'r' from the wordlist, guess it, get the number of matched chars in return, say 'm'. Now shorten the wordlist by
filtering and taking in ONLY those words which match 'm' characters with the word we used 'r'. [lower accuracy] O(n) time, O(n) space

[Not Accepted] Option 2: For every word, find the 0 matching characters with every other word. At the end for every word we have number of times
it has 0 matching chars with other words. The word which has the LEAST number of 0 matches with other words is probably the best candidate
Guess that word and get return value m, filter in only those words with m chars matching. Then repeat the 0 matching process again, again,
again, etc. [better accuracy] O(n^2) time, O(n) space

[Best] Option 3: Find count of every character in every index (words are 6 letters each). Then calculate word with highest score i.e.
most common chars with other chars. Use that to guess it. Then filter in array and repeat O(n) time, O(n) space

https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison

 */
public class GuessTheWord {

    interface Master {
        int guess(String word);
    }

    int getNumMatches(String s1, String s2) { //they are all of length 6
        int matches = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) == s2.charAt(i))
                matches++;
        }
        return matches;
    }

    //Option 1: Random guess then match [Accepted]
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; ++i) { //10 attempts
            Random rand = new Random();
            int idx = rand.nextInt(wordlist.length); // [0 to len - 1]
            String guessWord = wordlist[idx];
            int numMatches = master.guess(guessWord);
            if (numMatches == 6)
                break;
            //filter in words in wordlist with numMatches matches with guessWord
            List<String> newList = new ArrayList<>();
            for (String word : wordlist) {
                if (!word.equals(guessWord) && getNumMatches(word, guessWord) == numMatches)
                    newList.add(word);
            }
            wordlist = newList.toArray(new String[0]);
        }
    }

    //Option 3: Give every word a score which is the sum of frequency of each of its characters in each position across all the words
    //This is the score of similarity of the word with the other words
    //The higher the score, the more the word is similar with other words
    //BEST approach
    public void findSecretWord3(String[] wordlist, Master master) {

        for (int i = 0; i < 10; ++i) {
            //create score-chart
            int[][] scores = new int[6][26];
            for (String word : wordlist) {
                for (int j = 0; j < 6; ++j) {
                    scores[j][word.charAt(j) - 'a']++;
                }
            }

            //find word with the best overall score
            int bestScore = Integer.MIN_VALUE;
            String currGuess = null;
            for (String word : wordlist) {
                //calculate score
                int score = 0;
                for(int j = 0; j < 6; ++j) {
                    score += scores[j][word.charAt(j) - 'a'];
                }
                if(score > bestScore) {
                    bestScore = score;
                    currGuess = word;
                }
            }
            //guess word
            int numMatches = master.guess(currGuess);
            if(numMatches == 6)
                break;

            //filter in similar words
            List<String> newWordList = new ArrayList<>();
            for (String word : wordlist) {
                if (!word.equals(currGuess) && getNumMatches(word, currGuess) == numMatches) {
                    newWordList.add(word);
                }
            }
            wordlist = newWordList.toArray(new String[0]);
        }

    }

    //Option 2: Minimize worst case then match [Not accepted]
    public void findSecretWord2(String[] wordlist, Master master) {

        for (int attempt = 0; attempt < 10; attempt++) { //will do 10 attempts total
            if (wordlist.length == 1) {
                master.guess(wordlist[0]);
                break;
            }

            Map<String, Integer> stringZeroMatches = new HashMap<>();

            //For each string, find how many strings it has 0 matching chars with
            for (int i = 0; i < wordlist.length; ++i) {
                for (int j = i + 1; j < wordlist.length; ++j) {
                    if (!stringZeroMatches.containsKey(wordlist[i]))
                        stringZeroMatches.put(wordlist[i], 0);
                    if (!stringZeroMatches.containsKey(wordlist[j]))
                        stringZeroMatches.put(wordlist[j], 0);

                    int numMatches = getNumMatches(wordlist[i], wordlist[j]);
                    if (numMatches == 0) {
                        stringZeroMatches.put(wordlist[i], stringZeroMatches.get(wordlist[i]) + 1);
                        stringZeroMatches.put(wordlist[j], stringZeroMatches.get(wordlist[j]) + 1);
                    }
                }
            }

            //Find the string which has the LEAST number of 0 matching chars
            int min = Integer.MAX_VALUE;
            String currentGuess = "";
            for (Map.Entry<String, Integer> pair : stringZeroMatches.entrySet()) {
                if (pair.getValue() < min) {
                    min = pair.getValue();
                    currentGuess = pair.getKey();
                }
            }

            //got word with least number of 0 matches
            int numMatches = master.guess(currentGuess);
            if (numMatches == 6)
                break; //we are done

            //find words in wordList with "matches" matches and then do the next attempt on those
            List<String> newWordList = new ArrayList<>();
            for (String word : wordlist) {
                if (!word.equals(currentGuess) && getNumMatches(word, currentGuess) == numMatches) {
                    newWordList.add(word);
                }
            }
            wordlist = newWordList.toArray(new String[0]);
        }
    }

}
