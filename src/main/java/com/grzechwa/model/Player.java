package com.grzechwa.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Player {
    private String playerName;
    private int playerGold;
    private Character choosenCharacter;
    private ArrayList<District> districtsInHand;
    private int finishedDistrictsCounter;
    private int indexOnPlayersList;
    private boolean king;
    private boolean AI;

    public Player(){}
    public Player(String name, ArrayList<District>  districtsInHand,int indexOnPlayersList, boolean AI){
        this.playerName = name;
        this.playerGold = 2;
        this.choosenCharacter = null;
        this.districtsInHand = districtsInHand;
        this.finishedDistrictsCounter = 0;
        this.indexOnPlayersList = indexOnPlayersList;
        this.king = false;
        this.AI = AI;
    }
}
