package com.grzechwa.model;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Data
public class Player {
    private String playerName;
    private int playerGold;
    private Character choosenCharacter;
    private ArrayList<District> districtsInHand;
    private ArrayList<District> finishedDistricts;
    private int finishedDistrictsCounter;
    private int indexOnPlayersList;
    private int finalScore;
    private boolean firstWith8Districts;
    private boolean king;
    private boolean AI;

    public Player(){}
    public Player(String name, int playerGold, ArrayList<District>  districtsInHand,int indexOnPlayersList, boolean AI){
        this.playerName = name;
        this.playerGold = playerGold;
        this.choosenCharacter = null;
        this.districtsInHand = districtsInHand;
        this.finishedDistricts = new ArrayList<>();
        this.finishedDistrictsCounter = 0;
        this.indexOnPlayersList = indexOnPlayersList;
        this.firstWith8Districts = false;
        this.king = false;
        this.AI = AI;
    }

    public void addGold(int amountToAdd){
        playerGold+=amountToAdd;
    }

    public void removeGold(int amountToRemove){
        playerGold+=amountToRemove;
    }

    public void addDistrictsToHand(ArrayList<District> districtsToAdd){
        districtsInHand.addAll(districtsToAdd);
    }

    public void removeDistrictFromHand(District districtToRemove){
        getDistrictsInHand().remove(districtToRemove);
    }

    public void addDistrictToFinished(District districtToAdd){
        finishedDistricts.add(districtToAdd);
    }

    public void removeDistrictFromFinished(District districtToRemove){
        finishedDistricts.remove(districtToRemove);
    }

    public void incrementDistrictCounter(){
        finishedDistrictsCounter++;
    }

    public void decrementFinishedDistrictsCounter(){
        finishedDistrictsCounter--;
    }
}
