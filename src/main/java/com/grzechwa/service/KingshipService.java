package com.grzechwa.service;

import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Random;

public class KingshipService {
    private ArrayList<Player> players;

    public KingshipService(ArrayList<Player> players){
        this.players = players;
    }

    public void setKingRandomly(){
        removeKing();
        players.get(new Random().nextInt(players.size())).setKing(true);
    }

    public void setKing(Player player){
        player.setKing(true);
    }

    public Player getKing(){
        Player tempPlayer = null;
        for(Player player: players){
            if (player.isKing()){
                tempPlayer = player;
            }
        }
        return tempPlayer;
    }

    public int getKingIndex(){
        for(int i = 0; i < players.size(); i++){
            if (players.get(i).isKing()){
                return i;
            }
        }
        return -1;
    }

    public void removeKing(){
        for(Player player: players){
            if (player.isKing()){
                player.setKing(false);
            }
        }
    }
}