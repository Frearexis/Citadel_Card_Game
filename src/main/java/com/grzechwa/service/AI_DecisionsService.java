package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;

public class AI_DecisionsService {
    private DeckService deckService;
    private PlayerService playerService;
    private DistrictService districtService;
    private BuildingService buildingService;
    private KillingService killingService;
    private TheftService theftService;
    private DistrictDestroyingService districtDestroyingService;
    private DistrictSwappingService districtSwappingService;

    public AI_DecisionsService(DeckService deckService, CharacterService characterService, PlayerService playerService){
        this.deckService = deckService;
        this.playerService = playerService;
        this.districtService = new DistrictService();
        this.buildingService = new BuildingService(playerService,districtService,this);
        this.killingService = new KillingService(characterService);
        this.theftService = new TheftService(playerService,characterService);
        this.districtDestroyingService = new DistrictDestroyingService(playerService);
        this.districtSwappingService = new DistrictSwappingService(playerService,deckService);
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

    private void decideGoldOrDistrict(Player player){
        if(shouldTakeDistrict(player)){
            player.addDistrictsToHand(deckService.drawDistricts(1));
        }else{
            player.addGold(2);
        }
    }

    private boolean shouldTakeDistrict(Player player){
        ArrayList<District> districts = playerService.getDistrictsPossibleToBuildWithExtraGold(player, 2);
        if(player.getDistrictsInHand().isEmpty() || districts.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean shouldTakeGoldForDistrictsBeforeBuild(Player player){
        ArrayList<District> districtsPTB = playerService.getDistrictsPossibleToBuild(player);
        ArrayList<District> districtsColored = districtService.getPlayerColorDistricts(player,districtsPTB);
        return !districtsColored.isEmpty();
    }

    //Magician specific method
    public void takeDistrictsFromPlayerOrDeck(Player player){
        Player playerWithHighestAmountOfDistrictsInHand = playerService.getPlayerWithHighestAmountOfDistrictsInHand(player);
        if(playerWithHighestAmountOfDistrictsInHand.equals(player)){
            districtSwappingService.swapDistrictsWithDeck(player);
        }else{
            districtSwappingService.swapDistrictsBetweenPlayers(player,playerWithHighestAmountOfDistrictsInHand);
        }
    }

    private void playAsArchitect(Player player) {
        player.addGold(2);
        buildingService.buildAsArchitect(player);
        player.addDistrictsToHand(deckService.drawDistricts(2));
    }

    private void playAsAssassin(Player player) {
        killingService.killRandomCharacter();
        decideGoldOrDistrict(player);
        buildingService.buildAsAssassin(player);
    }

    private void playAsBishop(Player player) {
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
    }

    private void playAsGeneral(Player player) {
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        districtDestroyingService.basicDestroying(player);
    }

    private void playAsKing(Player player) {
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
    }
    //
    private void playAsMagician(Player player) {
        player.addGold(2);
        buildingService.buildAndSwapAsMagician(player);
    }

    private void playAsMerchant(Player player) {
        decideGoldOrDistrict(player);
        player.addGold(1);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
    }

    private void playAsThief(Player player) {
        theftService.robRandomCharacter();
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
    }
}
