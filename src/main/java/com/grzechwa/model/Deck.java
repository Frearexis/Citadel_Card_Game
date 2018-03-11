package com.grzechwa.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

public abstract class Deck {

    @Getter
    @Setter
    protected Stack<District> districts = new Stack<>();
    @Setter
    protected static boolean Empty = false;

    public boolean isEmpty(){
        return Deck.Empty;
    }

    protected void fillDeckWithDistrict(District districtToAdd, int amount){
        for(int i = 0; i < amount; i++){
            districts.push(districtToAdd);
        }
    }
}
