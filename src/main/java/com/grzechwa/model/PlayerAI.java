package com.grzechwa.model;

import java.util.ArrayList;

public class PlayerAI extends Player {
    public PlayerAI(String playerName,int playerGold, ArrayList<District> districtsInHand,int index, boolean isAI){
        super(playerName, playerGold, districtsInHand,index,isAI);
    }
}
