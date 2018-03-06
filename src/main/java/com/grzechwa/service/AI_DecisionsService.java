package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.Random;

public class AI_DecisionsService {
    private DeckService deckService;
    private CharacterService characterService;
    private KillingService killingService;
    private DistrictDestroyingService districtDestroyingService;
    private PlayerService playerService;
    private Random random;

    public AI_DecisionsService(DeckService deckService, CharacterService characterService, PlayerService playerService){
        this.deckService = deckService;
        this.characterService = characterService;
        this.playerService = playerService;
        this.killingService = new KillingService(characterService);
        this.districtDestroyingService = new DistrictDestroyingService(playerService);
        this.random = new Random();
    }

    public void play(Player player){
        switch (player.getChoosenCharacter().getCardName()){
            case "Architect" : playAsArchitect(player);
                break;
            case "Assassin" : playAsAssassin(player);
                break;
            case "Bishop" : playAsBishop(player);
                break;
            case "General" : playAsGeneral(player);
                break;
            case "King" : playAsKing(player);
                break;
            case "Magician" : playAsMagician(player);
                break;
            case "Merchant" : playAsMerchant(player);
                break;
            case "Thief" : playAsThief(player);
                break;
        }
    }

    private int countGoldForDistrictsBuilded(Player player){
        int colorMatchCounter = 0;
        for(District district : player.getFinishedDistricts()){
            if(player.getChoosenCharacter().getCardColor().equals(district.getCardColor())){
                colorMatchCounter++;
            }
        }
        return colorMatchCounter;
    }

    private void buildChoosenDistrict(Player player, District district){
        if(district.getDistrictCost() <= player.getPlayerGold() && district != null){
            player.removeGold(district.getDistrictCost());
            player.removeDistrictFromHand(district);
            player.addDistrictToFinished(district);
            player.incrementDistrictCounter();
        }
    }

    private boolean playerCanBuild(Player player){
        return player.getDistrictsInHand().size() > 0 && player.getPlayerGold() > 0 && player.getFinishedDistrictsCounter() <8;
    }

    private void playAsArchitect(Player player) {
        for(int i = 0; i < 3; i++){
            if(playerCanBuild(player)){
                buildChoosenDistrict(player,player.getCheapestDistrictInHand());
            }
            break;
        }
        player.addDistrictsToHand(deckService.drawDistricts(2));
    }

    private void playAsAssassin(Player player) {
        killingService.killRandomCharacter();
        buildChoosenDistrict(player,player.getMostExpensiveDistrictPossibleToBuild());
    }
    private void playAsBishop(Player player) {
        player.addGold(countGoldForDistrictsBuilded(player));
        if(playerCanBuild(player)){
            buildChoosenDistrict(player,player.getMostExpensiveDistrictPossibleToBuild());
        }
    }
    private void playAsGeneral(Player player) {
        player.addGold(countGoldForDistrictsBuilded(player));
        districtDestroyingService.basicDestroying(player);
        if(playerCanBuild(player)){
            buildChoosenDistrict(player,player.getMostExpensiveDistrictPossibleToBuild());
        }
    }
    private void playAsKing(Player player) {
        player.addGold(countGoldForDistrictsBuilded(player));
        if(playerCanBuild(player)){
            buildChoosenDistrict(player,player.getMostExpensiveDistrictPossibleToBuild());
        }
    }
    private void playAsMagician(Player player) {
    }
    private void playAsMerchant(Player player) {
        player.addGold(countGoldForDistrictsBuilded(player));
    }
    private void playAsThief(Player player) {
    }
}
