package com.grzechwa.service;

import com.grzechwa.model.Deck;
import com.grzechwa.model.District;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class DeckService {
    private DeckCreator deckCreator;
    @Getter
    @Setter
    private Deck deck;

    public DeckService(){
        this.deckCreator = new DeckCreator();
        this.deck = deckCreator.createStartingDeck();
    }

    public ArrayList<District> drawDistricts(int amount){
        ArrayList<District> drawnDistricts = new ArrayList<>();
        if(deck.isEmpty()){
            System.out.println("Koniec kart!"); //Alert do implementacji
        }else if(deck.getDistricts().size() > amount){
            for(int i = 0; i < amount; i++){
                drawnDistricts.add(deck.getDistricts().pop());
            }
        }else{
            while(!deck.isEmpty()){
                drawnDistricts.add(deck.getDistricts().pop());
            }
            deck.setEmpty(true);
            System.out.println("Koniec kart!"); //Alert do implementacji
        }
        return drawnDistricts;
    }
}