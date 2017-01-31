package com.anirudh.datastructures.Deck;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.anirudh.datastructures.Deck.Card.CardNumber.*;
import static com.anirudh.datastructures.Deck.Card.CardType.*;

/**
 * Created by paanir on 1/30/17.
 */
public class User {


    public Deck shuffle(Deck d) {
        Random rnd = ThreadLocalRandom.current();
        Card[] ar = d.cards;
        for (int i = ar.length - 1; i > 0; i--) {
            //Returns a pseudorandom, uniformly distributed {@code int} value
            //between 0 (inclusive) and the specified value (exclusive), drawn from
            //this random number generator's sequence.
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return d;
    }

    public Deck replaceLastWithTwoSpade(Deck deck) {
        Card[] ar = deck.cards;
        ar[1] = new Card(TWO, SPADE);
        return deck;
    }

}
