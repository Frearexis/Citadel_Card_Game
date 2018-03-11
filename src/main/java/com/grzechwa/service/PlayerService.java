package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.Bishop;
import com.grzechwa.model.cards.character.Thief;
import com.grzechwa.model.comparators.DistrictsInHandComparator;
import com.grzechwa.model.comparators.PlayersGoldComparator;
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
            player.setChoosenCharacter(null);
        }
    }

    public Player getRandomPlayer(ArrayList<Player> customPlayerList){
        return customPlayerList.get(random.nextInt(customPlayerList.size()));
    }

    public Player getThiefPlayer(ArrayList<Player> players){
        for(Player player : players){
            if(player.getChoosenCharacter().equals(new Thief())){
                return player;
            }
        }
        return null;
    }

    public ArrayList<District> getDistrictsPossibleToBuildWithExtraGold(Player player, int amount){
        ArrayList<District> districtsPossibleToBuild = new ArrayList<>();
        for(District districtInHand : player.getDistrictsInHand()){
            for(District districtBuilded : player.getFinishedDistricts()){
                if(player.getPlayerGold()+amount >= districtInHand.getDistrictCost() && !(districtInHand.equals(districtBuilded))){
                    districtsPossibleToBuild.add(districtInHand);
                }
            }
        }
        return districtsPossibleToBuild;
    }

    public ArrayList<District> getDistrictsPossibleToBuild(Player player){
        ArrayList<District> districtsPossibleToBuild = new ArrayList<>();
        for(District districtInHand : player.getDistrictsInHand()){
            if(player.getPlayerGold() >= districtInHand.getDistrictCost() && !(playerAlreadyFinishedDistrict(player,districtInHand))){
                    districtsPossibleToBuild.add(districtInHand);
                }
        }
        return districtsPossibleToBuild;
    }

    public boolean playerAlreadyFinishedDistrict(Player player, District district){
        for(District dist : player.getFinishedDistricts()){
            if(dist.equals(district)){
                return true;
            }
        }
        return false;
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
            if(player.getFinishedDistrictsCounter() < 8 && !(player.getChoosenCharacter().equals(new Bishop()))){
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

    public Player getPlayerWithHighestAmountOfGold(Player askingPlayer){
        Collections.sort(players, new PlayersGoldComparator());
        return getPlayerOtherThenAskingOne(askingPlayer);
    }

    public Player getPlayerWithHighestAmountOfDistrictsInHand(Player askingPlayer){
        Collections.sort(players,new DistrictsInHandComparator());
        if(players.get(0).equals(askingPlayer) && players.get(0).getDistrictsInHand().size() > players.get(1).getDistrictsInHand().size()){
            return askingPlayer;
        }
        return players.get(0);
    }
}
