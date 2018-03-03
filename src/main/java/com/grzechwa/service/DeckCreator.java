package com.grzechwa.service;

import com.grzechwa.model.ClassicDeck;
import com.grzechwa.model.Deck;
import com.grzechwa.model.District;
import com.grzechwa.model.cards.district.*;

import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

/*In future this Class will be responsible for creating decks based on players choice.
    for now it's simplified to create always the same card configuration (but in shuffled order)
*/
public class DeckCreator {
    private Deck deck;

    public Deck createStartingDeck(){
        deck = new ClassicDeck();
        shuffleDeck();
        return deck;
    }

    private Deck shuffleDeck(){
        Collections.shuffle(deck.getDistricts());
        return deck;
    }
}
