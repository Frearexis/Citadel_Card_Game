package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.Bishop;
import com.grzechwa.model.cards.character.Thief;
import com.grzechwa.model.comparators.DistrictsInHandComparator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class PlayerService {
    @Getter
    private ArrayList<Player> players;
    private KingshipService kingshipService;
    private Random random;

    public PlayerService(ArrayList<Player> players, KingshipService kingshipService, Random random){
        this.players = players;
        this.kingshipService = kingshipService;
        this.random = random;
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

    public ArrayList<Player> getPlayersStartingFromKing(ArrayList<Player> players){
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(kingshipService.getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,kingshipService.getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        return orderedPlayersList;
    }

    public ArrayList<Player> getPlayersInOrderOfCharactersAppearance(ArrayList<Player> players){
        players.sort(Comparator.comparing(player -> player.getChoosenCharacter().getTurnOfAppearance()));
        return players;
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

    public Player getPlayerWithHighestAmountOfDistrictsInHand(Player askingPlayer){
        ArrayList<Player> tempPlayers = new ArrayList<>(players);
        Collections.sort(tempPlayers,Collections.reverseOrder(new DistrictsInHandComparator()));
        if(tempPlayers.get(0).equals(askingPlayer) && tempPlayers.get(0).getDistrictsInHand().size() > tempPlayers.get(1).getDistrictsInHand().size()){
            return askingPlayer;
        }
        return tempPlayers.get(0);
    }

    public boolean isFirstWith8DistrictsAmongPlayers(){
        for(Player player : players){
            if(player.isFirstWith8Districts()) return false;
        }
        return true;
    }
}
