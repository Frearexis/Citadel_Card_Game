package com.grzechwa.model.decks;

import com.grzechwa.model.Deck;
import com.grzechwa.model.cards.district.*;

public final class ClassicDeck extends Deck {

    public ClassicDeck() {
        fillDeckWithDistrict(new Battlefield(),3);
        fillDeckWithDistrict(new Castle(),4);
        fillDeckWithDistrict(new Cathedral(),2);
        fillDeckWithDistrict(new Church(),3);
        fillDeckWithDistrict(new Docks(),2);
        fillDeckWithDistrict(new Dragon_Gate(),1);
        fillDeckWithDistrict(new Fortress(),2);
        fillDeckWithDistrict(new Harbor(),3);
        fillDeckWithDistrict(new Manor(),5);
        fillDeckWithDistrict(new Market(),4);
        fillDeckWithDistrict(new Monastery(),3);
        fillDeckWithDistrict(new Palace(), 3);
        fillDeckWithDistrict(new Prison(),3);
        fillDeckWithDistrict(new Tavern(),5);
        fillDeckWithDistrict(new Temple(),3);
        fillDeckWithDistrict(new Town_Hall(),2);
        fillDeckWithDistrict(new Warehouse(),3);
        fillDeckWithDistrict(new Watchtower(),3);
    }
}
