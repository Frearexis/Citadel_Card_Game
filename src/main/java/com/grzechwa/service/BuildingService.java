package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.Architect;

import java.util.ArrayList;

public class BuildingService {

    private PlayerService playerService;
    private DistrictService districtService;
    private AI_DecisionsService ai_decisionsService;

    public BuildingService(PlayerService playerService, DistrictService districtService, AI_DecisionsService ai_decisionsService){
        this.playerService = playerService;
        this.districtService = districtService;
        this.ai_decisionsService = ai_decisionsService;
    }

    public boolean playerCanBuild(Player player, ArrayList<District> districtsPTB){
        return  player.getPlayerGold() > 0 &&
                player.getFinishedDistrictsCounter() <8 &&
                !districtsPTB.isEmpty();
    }

    public void buildChoosenDistrict(Player player, District district){
        player.removeGold(district.getDistrictCost());
        player.removeDistrictFromHand(district);
        player.addDistrictToFinished(district);
        player.incrementDistrictCounter();
        System.out.println("\t"+player.getPlayerName()+" build "+ district.getCardName()+ " "+ district.getDistrictCost());
    }

    public int countGoldForDistrictsBuilded(Player player){
        int colorMatchCounter = 0;
        for(District district : player.getFinishedDistricts()){
            if(player.getChoosenCharacter().getCardColor().equals(district.getCardColor())){
                colorMatchCounter++;
            }
        }
        System.out.println(player.getPlayerName()+ "Gold for districts builded");
        return colorMatchCounter;
    }

    public void buildAsArchitect(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        for(int i = 0; i < 3; i++){
            if(playerCanBuild(player,districtsPossibleToBuild)){
                District districtToBuild = districtService.getCheapestDistrict(districtsPossibleToBuild);
                buildChoosenDistrict(player,districtToBuild);
                districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
            }
        }
    }

    public void buildAsAssassin(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if(playerCanBuild(player,districtsPossibleToBuild)){
            buildChoosenDistrict(player,districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
        }
    }

    public void buildAndSwapAsMagician(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if(playerCanBuild(player,districtsPossibleToBuild)){
            buildChoosenDistrict(player,districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
            ai_decisionsService.takeDistrictsFromPlayerOrDeck(player);
        }else{
            ai_decisionsService.takeDistrictsFromPlayerOrDeck(player);
            districtsPossibleToBuild  = playerService.getDistrictsPossibleToBuild(player);
            if(playerCanBuild(player,districtsPossibleToBuild)){
                buildChoosenDistrict(player,districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
            }
        }
    }

    public void buildAfterGoldTaken(Player player){
        player.addGold(countGoldForDistrictsBuilded(player));
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if(playerCanBuild(player,districtsPossibleToBuild)) {
            buildChoosenDistrict(player, districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
        }
    }

    public void buildBeforeGoldTaken(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if (playerCanBuild(player,districtsPossibleToBuild)) {
            District coloredDistrict = districtService.getMostExpensiveDistrict(districtService.getPlayerColorDistricts(player,districtsPossibleToBuild));
            if(coloredDistrict != null) {
                buildChoosenDistrict(player, coloredDistrict);
            }else{
                buildChoosenDistrict(player, districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
            }
            player.addGold(countGoldForDistrictsBuilded(player));
        }
    }
}
