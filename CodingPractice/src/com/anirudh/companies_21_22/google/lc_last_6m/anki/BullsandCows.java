package com.anirudh.companies_21_22.google.lc_last_6m.anki;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
299. Bulls and Cows
Medium

1194

1222

Add to List

Share
You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:

The number of "bulls", which are digits in the guess that are in the correct position.
The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.



Example 1:

Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810"
Example 2:

Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
  |      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 */

/*
Option 1:
increment bull as you iterate. for cows, make 2 maps of <int -> frequency>.
then iterate through secret's map and get math.min() of both strings
O(n) space O(n) time

ACTUALLY, key of map is a digit, so atmost 10 digits
so, it is O(1) space, O(n) time
 */
public class BullsandCows {
    public String getHint(String secret, String guess) {
        int numBulls = 0;
        int numCows = 0;
        Map<Character, Integer> potCowsSecret = new HashMap<>(); //integer -> freq
        Map<Character, Integer> potCowsGuess = new HashMap<>();
        for (int i = 0; i < secret.length(); ++i) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                numBulls++;
            } else {
                potCowsSecret.put(s, potCowsSecret.getOrDefault(s, 0) + 1);
                potCowsGuess.put(g, potCowsGuess.getOrDefault(g, 0) + 1);
            }
        }
        for (char c : potCowsSecret.keySet()) {
            if (potCowsGuess.containsKey(c)) {
                numCows += Math.min(potCowsSecret.get(c), potCowsGuess.get(c));
            }
        }
        return numBulls + "A" + numCows + "B";
    }
}
