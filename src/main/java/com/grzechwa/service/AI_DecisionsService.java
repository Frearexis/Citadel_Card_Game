package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.Random;

public class AI_DecisionsService {
    private DeckService deckService;
    private Random random;

    public AI_DecisionsService(DeckService deckService){
        this.deckService = deckService;
        this.random = new Random();
    }

    //TODO: Implement switch statement for each possible character and his simplified playstyle in other methods in this class.
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

    private void buildExpensiveDistrict(Player player){
        if(playerCanBuild(player)){
            //Collections.sort(player.getDistrictsInHand());
            //player.getDistrictsInHand().forEach(s ->System.out.println(s.getCardName() + " " + s.getDistrictCost()));
            for(District district : player.getDistrictsInHand()){
                if(district.getDistrictCost() <= player.getPlayerGold()){
                    player.removeGold(district.getDistrictCost());
                    player.removeDistrictFromHand(district);
                    player.addDistrictToFinished(district);
                    player.setFinishedDistrictsCounter(player.getFinishedDistrictsCounter()+1);
                }
                break;
            }
        }
    }

    private void buildCheapDistrict(Player player){

    }

    private boolean playerCanBuild(Player player){
        return player.getDistrictsInHand().size() > 0 && player.getPlayerGold() > 0;
    }

    private void playAsArchitect(Player player) {
        buildCheapDistrict(player);
        deckService.drawDistricts(2);
    }

    private void playAsAssassin(Player player) {
    }
    private void playAsBishop(Player player) {
    }
    private void playAsGeneral(Player player) {
    }
    private void playAsKing(Player player) {
    }
    private void playAsMagician(Player player) {
    }
    private void playAsMerchant(Player player) {
    }
    private void playAsThief(Player player) {
    }
}
