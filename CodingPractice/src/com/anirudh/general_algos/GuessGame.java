package com.anirudh.general_algos;

/**
 * Created by paanir on 2/8/17.
 */

/*

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.
 */
public class GuessGame {
    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

    public int guess(int i) {
        //dummy fn
        return 0;
    }

    public int guessAux(int start, int end) {
        int mid = start + (end - start) / 2;

        int hint = guess(mid);
        if (hint == 0)
            return mid;
        else if (hint == 1)
            return guessAux(mid + 1, end);
        else
            return guessAux(start, mid - 1);
    }

    public int guessNumber(int n) {
        return guessAux(1, n);
    }
}
