package com.grzechwa.service;

import com.grzechwa.model.Card;
import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//TODO Implement various methods which will determine behaviour of AI players.
public class PlayerService {
    @Getter
    private ArrayList<Player> players;
    private Random random;

    public PlayerService(ArrayList<Player> players){
        this.players = players;
        this.random = new Random();
    }

    public void resetPlayersChoosenCharacters(){
        for(Player player : players){
            player.getChoosenCharacter().setCurrentOwner(null);
        }
    }

    public Player getRandomPlayer(ArrayList<Player> customPlayerList){
        return customPlayerList.get(random.nextInt(customPlayerList.size()));
    }

    //It's not perfect yet as this method will always return the last player when it comes to tie.
    public Player getPlayerWithHighestAmountOfDistrictsBuilded(Player askingPlayer){
        Player playerWorthDestroying = getPlayerOtherThenAskingOne(askingPlayer);
        for(Player player : players){
            if(player.getFinishedDistrictsCounter() > playerWorthDestroying.getFinishedDistrictsCounter() && !player.equals(askingPlayer)) {
                playerWorthDestroying = player;
            }
        }
        return playerWorthDestroying;
    }

    public ArrayList<Player> getPlayersWithOneCostDistrictBuilded(Player askingPlayer){
        ArrayList<Player> playersWithCheapestDistricts = new ArrayList<>();
        for(Player player : players){
            if(!player.equals(askingPlayer)) {
                for (District district : player.getFinishedDistricts()) {
                    if(district.getDistrictCost() == 1) {
                        playersWithCheapestDistricts.add(player);
                        break;
                    }
                }
            }
        }
        return playersWithCheapestDistricts;
    }

    public Player getRandomPlayerPossibleToDestroy(ArrayList<Player> players){
        ArrayList<Player> playersWithLessThen8Districts = new ArrayList<>();
        for(Player player : players){
            if(player.getFinishedDistrictsCounter() < 8){
                playersWithLessThen8Districts.add(player);
            }
        }
        if(playersWithLessThen8Districts.size() > 0){
            return getRandomPlayer(playersWithLessThen8Districts);
        }
        return null;
    }

    private Player getPlayerOtherThenAskingOne(Player askingPlayer){
        return players.get(0).equals(askingPlayer) ? players.get(1):players.get(0);
    }

    /*
    public Player getPlayerWithHighestAmountOfGold(){}
    public Player getPlayerWithHighestAmountOfDistrictsFinished(){}
    public Player getPlayerWithHighestAmountOfDistrictsInHand(){}
    */
}
