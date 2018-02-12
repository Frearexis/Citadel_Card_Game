package com.grzechwa.service;

import com.grzechwa.model.Deck;
import com.grzechwa.model.District;
import com.grzechwa.model.cards.district.*;

import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;


public class DeckCreator {
    private static Stack<District> SHUFFELED_FILLED_DECK = new Stack<District>();

    private static final Castle CASTLE = new Castle();
    private static final Dragon_Gate DRAGON_GATE = new Dragon_Gate();
    private static final Harbor HARBOR = new Harbor();
    private static final Manor MANOR = new Manor();
    private static final Market MARKET = new Market();
    private static final Monastery MONASTERY = new Monastery();
    private static final Prison PRISON = new Prison();
    private static final Town_Hall TOWN_HALL = new Town_Hall();

    public static Deck createDeck(){
        fillShuffleDeck();
        Deck deck = new Deck(SHUFFELED_FILLED_DECK);
        return deck;
    }

    private static void fillShuffleDeck(){
        SHUFFELED_FILLED_DECK.addAll(Arrays.asList(
                CASTLE,DRAGON_GATE,HARBOR,MANOR,MARKET,MONASTERY,PRISON,TOWN_HALL));
        SHUFFELED_FILLED_DECK = shuffleDeck(multipleDistricts(SHUFFELED_FILLED_DECK));
    }

    private static Stack<District> multipleDistricts(Stack<District> districtsTemplate){
        Stack<District> unshuffledFilledDeck = new Stack<District>();
        for(District district : districtsTemplate){
            for(int i = 0; i < 2; i++)
                unshuffledFilledDeck.add(district);
        }
        return unshuffledFilledDeck;
    }

    private static Stack<District> shuffleDeck(Stack<District> deckToShuffle){
        Collections.shuffle(deckToShuffle);
        return deckToShuffle;
    }
}
