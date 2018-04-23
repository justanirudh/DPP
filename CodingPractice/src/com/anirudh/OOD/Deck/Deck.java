package com.anirudh.OOD.Deck;

/**
 * Created by paanir on 1/30/17.
 */
public class Deck {

    int deckSize = 10;

    Card[] cards;

    private static Deck d;

    private Deck(Card[] cards) {
        if (cards.length < deckSize)
            throw new RuntimeException("min length 10 reqd");
        this.cards = cards;
    }

    public static Deck getDeck(Card[] cards) {
        if (d == null)
            d = new Deck(cards);
        else
            System.out.println("Sorry, We have only 1 deck. Cannot use supplied cards");
        return d;
    }
}
