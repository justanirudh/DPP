package com.anirudh.OOD.Deck;

import static com.anirudh.OOD.Deck.Card.CardNumber.*;
import static com.anirudh.OOD.Deck.Card.CardType.*;

/**
 * Created by paanir on 1/30/17.
 */
public class UseDeck {

    public static void main(String[] args) {

        Card[] cards = {new Card(ACE, CLUBS), new Card(TWO, DIAMOND), new Card(KING, SPADE), new Card(QUEEN, HEART),
                new Card(FOUR, CLUBS), new Card(SEVEN, DIAMOND), new Card(EIGHT, SPADE), new Card(JACK, HEART),
                new Card(FIVE, CLUBS), new Card(TEN, DIAMOND)};

        Deck deck = Deck.getDeck(cards);

        User you = new User();

        Deck shuffD = you.shuffle(deck);

        for (Card c : shuffD.cards)
            System.out.println(c.value + " " + c.type);

    }

}
