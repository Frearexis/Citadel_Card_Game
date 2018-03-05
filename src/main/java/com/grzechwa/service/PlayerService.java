package com.grzechwa.service;

import com.grzechwa.model.Player;
import lombok.Getter;

import java.util.ArrayList;

//TODO Implement various methods which will determine behaviour of AI players.
public class PlayerService {
    @Getter
    private ArrayList<Player> players;

    public PlayerService(ArrayList<Player> players){
        this.players = players;
    }

    /*
    public Player getPlayerWithHighestAmountOfGold(){}
    public Player getPlayerWithHighestAmountOfDistrictsFinished(){}
    public Player getPlayerWithHighestAmountOfDistrictsInHand(){}
    */

}
