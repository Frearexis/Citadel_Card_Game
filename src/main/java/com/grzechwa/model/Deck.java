package com.grzechwa.model;

import com.grzechwa.model.cards.district.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

public abstract class Deck {

    protected static Battlefield battlefield;
    protected static Castle castle;
    protected static Cathedral cathedral;
    protected static Church church;
    protected static Docks docks;
    protected static Dragon_Gate dragon_gate;
    protected static Fortress fortress;
    protected static Harbor harbor;
    protected static Manor manor;
    protected static Market market;
    protected static Monastery monastery;
    protected static Palace palace;
    protected static Prison prison;
    protected static Tavern tavern;
    protected static Temple temple;
    protected static Town_Hall town_hall;
    protected static Warehouse warehouse;
    protected static Watchtower watchtower;

    @Getter
    @Setter
    protected static Stack<District> districts = new Stack<>();
    @Setter
    protected static boolean Empty = false;

    public boolean isEmpty(){
        return Deck.Empty;
    }

    protected void fillDeckWithDistrict(District districtToAdd, int amount){
        for(int i = 0; i < amount; i++){
            districts.add(districtToAdd);
        }
    }
}
