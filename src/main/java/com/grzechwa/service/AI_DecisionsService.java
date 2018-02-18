package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.Player;

import java.util.Random;

public class AI_DecisionsService {
    private DeckService deckService;
    private Random random;

    public AI_DecisionsService(){
        this.deckService = new DeckService();
        this.random = new Random();
    }

    public void play(Player player, Character character){
        //TODO Implement switch statement for each possible character and his simplified playstyle in other methods in this class.
    }

}
