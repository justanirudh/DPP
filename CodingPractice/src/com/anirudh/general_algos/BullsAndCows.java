package com.anirudh.general_algos;

import scala.Char;

import java.util.HashMap;

/**
 * Created by paanir on 12/19/16.
 */
public class BullsAndCows {

    public static String getHint(String secret, String guess) {
        int bullCount = 0;
        int cowCount = 0;

        HashMap<Character, Integer> secretMap = new HashMap();

        for (char c : secret.toCharArray()) {
            Character cC = c;
            if (secretMap.containsKey(cC))
                secretMap.put(cC, secretMap.get(cC) + 1);
            else
                secretMap.put(cC, 1);
        }

        StringBuilder tempGuess = new StringBuilder();
        for (int i = 0; i < guess.length(); ++i) { //get bulls
            Character guessC = guess.charAt(i);
            if (secret.charAt(i) == guessC) {
                bullCount++;
                secretMap.put(guessC, secretMap.get(guessC) - 1); //changing secretMap
                if (secretMap.get(guessC) == 0)
                    secretMap.remove(guessC);
            } else
                tempGuess.append(guessC); //changing guess
        }

        for (int i = 0; i < tempGuess.length(); ++i) { //get cows
            Character guessC = tempGuess.charAt(i);
            if (secretMap.containsKey(guessC)) {
                cowCount++;
                secretMap.put(guessC, secretMap.get(guessC) - 1);
                if (secretMap.get(guessC) == 0)
                    secretMap.remove(guessC);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bullCount + "A");
        sb.append(cowCount + "B");
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "1807";
        String s2 = "7810";
        String s3 = "1122";
        String s4 = "1222";
        System.out.println(getHint(s3, s4));
    }
}
