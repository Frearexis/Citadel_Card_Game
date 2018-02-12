package com.grzechwa.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

public class Deck {
    @Getter
    @Setter
    private static Stack<District> districts;
    @Setter
    private static boolean Empty = false;

    public Deck(Stack<District> deck) {
        Deck.districts = deck;
    }
    public boolean isEmpty(){
        return Deck.Empty;
    }
}
