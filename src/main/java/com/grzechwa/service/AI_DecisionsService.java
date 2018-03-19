package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.King;

import java.util.ArrayList;

public class AI_DecisionsService {
    private PlayerService playerService;
    private DistrictService districtService;
    private DeckService deckService;
    private BuildingService buildingService;
    private KingshipService kingshipService;
    private KillingService killingService;
    private TheftService theftService;
    private DistrictDestroyingService districtDestroyingService;
    private DistrictSwappingService districtSwappingService;

    public AI_DecisionsService(PlayerService playerService,
                               DistrictService districtService,
                               DeckService deckService,
                               BuildingService buildingService,
                               KingshipService kingshipService,
                               KillingService killingService,
                               TheftService theftService,
                               DistrictDestroyingService districtDestroyingService,
                               DistrictSwappingService districtSwappingService){
        this.playerService = playerService;
        this.districtService = districtService;
        this.deckService = deckService;
        this.buildingService = buildingService;
        this.kingshipService = kingshipService;
        this.killingService = killingService;
        this.theftService = theftService;
        this.districtDestroyingService = districtDestroyingService;
        this.districtSwappingService = districtSwappingService;
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
        if(player.getPlayerGold() <= 2){
            return false;
        }else if(player.getPlayerGold() >= 8){
            return true;
        }else if(player.getDistrictsInHand().isEmpty() || districts.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean shouldTakeGoldForDistrictsBeforeBuild(Player player){
        ArrayList<District> districtsPTB = playerService.getDistrictsPossibleToBuild(player);
        ArrayList<District> districtsColored = districtService.getPlayerColorDistricts(player,districtsPTB);
        return districtsColored.isEmpty();
    }

    //Magician specific method
    public void takeDistrictsFromPlayerOrDeck(Player player){
        Player playerWithDistrictsToSwap = playerService.getPlayerWithHighestAmountOfDistrictsInHand(player);
        if(playerWithDistrictsToSwap.equals(player)){
            districtSwappingService.swapDistrictsWithDeck(player);
        }else{
            districtSwappingService.swapDistrictsBetweenPlayers(player,playerWithDistrictsToSwap);
        }
    }

    //Magician specific method
    private boolean shouldBuildBeforeSwap(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if(buildingService.playerCanBuild(player,districtsPossibleToBuild)){
            return true;
        }
        return false;
    }

    public void printPlayerRoundSummary(Player player){
        System.out.printf("\n\n%s %s gold %d\nin hand: ",player.getPlayerName(),player.getChoosenCharacter(), player.getPlayerGold());
        if(player.getDistrictsInHand().isEmpty()){
            System.out.print("---Empty---");
        }else{
            player.getDistrictsInHand().forEach(s->System.out.printf("%s ", s));
        }
        System.out.printf("\nfinished districts %d: ",player.getFinishedDistrictsCounter());
        if (player.getFinishedDistrictsCounter() == 0){
            System.out.print("---Empty---");
        }else{
            player.getFinishedDistricts().forEach(s->System.out.printf("%s ", s));
        }

    }

    private void playAsArchitect(Player player) {
        printPlayerRoundSummary(player);
        player.addGold(2);
        buildingService.buildAsArchitect(player);
        player.addDistrictsToHand(deckService.drawDistricts(2));
        printPlayerRoundSummary(player);
    }

    private void playAsAssassin(Player player) {
        printPlayerRoundSummary(player);
        killingService.killRandomCharacter();
        decideGoldOrDistrict(player);
        buildingService.buildExpensive(player);
        printPlayerRoundSummary(player);
    }

    private void playAsBishop(Player player) {
        printPlayerRoundSummary(player);
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        printPlayerRoundSummary(player);
    }

    private void playAsGeneral(Player player) {
        printPlayerRoundSummary(player);
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        districtDestroyingService.basicDestroying(player);
        printPlayerRoundSummary(player);
    }

    private void playAsKing(Player player) {
        if (player.getChoosenCharacter().equals(new King()) && !player.isKing()) {
            kingshipService.removeKing();
            kingshipService.setKing(player);
        }
        printPlayerRoundSummary(player);
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        printPlayerRoundSummary(player);
    }

    private void playAsMagician(Player player) {
        printPlayerRoundSummary(player);
        player.addGold(2);
        if(shouldBuildBeforeSwap(player)){
            buildingService.buildExpensive(player);
            takeDistrictsFromPlayerOrDeck(player);
        }else{
            takeDistrictsFromPlayerOrDeck(player);
            buildingService.buildExpensive(player);
        }
        printPlayerRoundSummary(player);
    }

    private void playAsMerchant(Player player) {
        printPlayerRoundSummary(player);
        decideGoldOrDistrict(player);
        player.addGold(1);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        printPlayerRoundSummary(player);
    }

    private void playAsThief(Player player) {
        printPlayerRoundSummary(player);
        theftService.markRandomAsRobbed();
        decideGoldOrDistrict(player);
        if(shouldTakeGoldForDistrictsBeforeBuild(player)){
            buildingService.buildAfterGoldTaken(player);
        }else{
            buildingService.buildBeforeGoldTaken(player);
        }
        printPlayerRoundSummary(player);
    }
}