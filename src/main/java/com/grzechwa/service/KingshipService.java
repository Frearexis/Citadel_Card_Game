package com.grzechwa.service;

import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

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

    public int getKingIndex(){
        return IntStream.range(0, players.size())
                .filter(i -> players.get(i).isKing())
                .findFirst()
                .getAsInt();
    }

    public void removeKing(){
        for(Player player: players){
            if (player.isKing()){
                player.setKing(false);
            }
        }
    }
}