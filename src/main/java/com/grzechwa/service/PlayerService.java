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
import java.util.stream.Collectors;

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
        players.forEach(p -> p.setChoosenCharacter(null));
    }

    public Player getRandomPlayer(ArrayList<Player> customPlayerList){
        return customPlayerList.get(random.nextInt(customPlayerList.size()));
    }

    public Player getThiefPlayer(ArrayList<Player> players){
        return players.stream()
                .filter(p -> p.getChoosenCharacter().equals(new Thief()))
                .findFirst().orElseGet(null); //should be changed to orElseThrow
    }

    public ArrayList<Player> getPlayersStartingFromKing(ArrayList<Player> players){
        players.forEach( x ->System.out.println(x.getPlayerName() + " " + x.isKing()));
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(kingshipService.getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,kingshipService.getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        orderedPlayersList.forEach( x ->System.out.println(x.getPlayerName() + " " + x.isKing()));
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
        return player.getDistrictsInHand().stream()
                .filter(d -> player.getPlayerGold() >= d.getDistrictCost())
                .filter(d -> !playerAlreadyFinishedDistrict(player,d))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean playerAlreadyFinishedDistrict(Player player, District district){
       return player.getFinishedDistricts().stream()
                .anyMatch(d -> d.equals(district));
    }

    public ArrayList<Player> getPlayersWithOneCostDistrictBuilded(Player askingPlayer){
        return players.stream()
                .filter(p -> !p.equals(askingPlayer))
                .filter(Player::hasOneCostDistrictFinished) //static method should be replaced
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Player getRandomPlayerPossibleToDestroy(ArrayList<Player> players){
        ArrayList<Player> playersWithLessThen8Districts = new ArrayList<>();
        players.stream()
                .filter(p -> p.getFinishedDistrictsCounter() < 8)
                .filter(p -> !p.getChoosenCharacter().equals(new Bishop()))
                .forEach(playersWithLessThen8Districts::add);
        if(!playersWithLessThen8Districts.isEmpty()){
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

    public boolean isFirstWith8DistrictsAmongPlayers() {
        return players.stream().anyMatch(Player::isFirstWith8Districts);
    }
}
