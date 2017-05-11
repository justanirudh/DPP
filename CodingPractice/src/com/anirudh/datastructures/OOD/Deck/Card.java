package com.anirudh.datastructures.OOD.Deck;

/**
 * Created by paanir on 1/30/17.
 */
public class Card {

    enum CardNumber {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    enum CardType {DIAMOND, CLUBS, HEART, SPADE}


    CardNumber value;
    CardType type;

    public Card(CardNumber value,  CardType ct) {
        this.value = value;
        this.type = ct;
    }
}
