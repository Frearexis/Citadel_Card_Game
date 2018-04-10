package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;

public class BuildingService {

    private PlayerService playerService;
    private DistrictService districtService;

    public BuildingService(PlayerService playerService, DistrictService districtService){
        this.playerService = playerService;
        this.districtService = districtService;
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
        checkFor8Districts(player);

        System.out.println("\n\t"+player.getPlayerName()+" build "+ district.getCardName()+ " "+ district.getDistrictCost());
    }

    private void checkFor8Districts(Player player){
        if(player.getFinishedDistrictsCounter() == 8){
            if(playerService.isFirstWith8DistrictsAmongPlayers()){
                player.setFirstWith8Districts(true);
            }else{
                player.setNextWith8Districts(true);
            }
        }
    }

    public int countGoldForDistrictsBuilded(Player player){
        return (int)player.getFinishedDistricts().stream()
                .filter(x -> player.getChoosenCharacter().getCardColor().equals(x.getCardColor()))
                .count();
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

    public void buildExpensive(Player player){
        ArrayList<District> districtsPossibleToBuild = playerService.getDistrictsPossibleToBuild(player);
        if(playerCanBuild(player,districtsPossibleToBuild)){
            buildChoosenDistrict(player,districtService.getMostExpensiveDistrict(districtsPossibleToBuild));
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
