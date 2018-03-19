package com.grzechwa.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PlayerAI extends Player {
    public PlayerAI(String playerName, int playerGold, ObservableList districtsInHand, int index, boolean isAI){
        super(playerName, playerGold, districtsInHand,index,isAI);
    }
}
