package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;

public class CountFinalScoreService {
    private static final int FIRST_WITH_8_DISTRICT_POINTS = 4;
    private static final int DISTRICTS_IN_ALL_COLORS_POINTS = 3;
    private static final int NEXT_WITH_8_DISTRICT_POINTS = 2;
    private ArrayList<Player> players;

    public CountFinalScoreService(ArrayList<Player> players){
        this.players = players;
    }

    public void countPointsForAllPlayers(){
        for(Player player : players){
            player.setFinalScore(
                    countPointsForFirstWith8Districts(player)
                   +countPointsForNextWith8Districts(player)
                   +countPointsForDistrictsBuilded(player)
                   +districtsInAllColorsBonus(player));
            printPlayerFinalSummary(player);
        }
    }

    //For debuging in text mode
    public void printPlayerFinalSummary(Player player){
        System.out.printf("\n\n%s points for first with 8 %d\n points for next w8 %d\n points for districts %d\n all colors %d\n",
                player.getPlayerName(),
                countPointsForFirstWith8Districts(player),
                countPointsForNextWith8Districts(player),
                countPointsForDistrictsBuilded(player),
                districtsInAllColorsBonus(player));
        player.getFinishedDistricts().forEach(s-> System.out.println(s.getCardName()+" "+s.getCardColor()+" "+s.getDistrictCost()));
    }

    private int countPointsForFirstWith8Districts(Player player){
        return player.isFirstWith8Districts() ? FIRST_WITH_8_DISTRICT_POINTS : 0;
    }

    private int countPointsForNextWith8Districts(Player player){
        return (!player.isFirstWith8Districts() && player.getFinishedDistrictsCounter() == 8) ? NEXT_WITH_8_DISTRICT_POINTS : 0;
    }

    private int countPointsForDistrictsBuilded(Player player){
        int sum = 0;
        for(District district : player.getFinishedDistricts()){
            sum += district.getDistrictCost();
        }
        return sum;
    }

    private int districtsInAllColorsBonus(Player player){
        boolean hasYellowDistrict = false;
        boolean hasPurpleDistrict = false;
        boolean hasGreenDistrict = false;
        boolean hasBlueDistrict = false;
        boolean hasRedDistrict = false;
        for(District district : player.getFinishedDistricts()){
            switch (district.getCardColor()){
                case "yellow":    hasYellowDistrict = true;
                    break;
                case "purple":  hasPurpleDistrict = true;
                    break;
                case "green":   hasGreenDistrict = true;
                    break;
                case "blue":    hasBlueDistrict = true;
                    break;
                case "red":     hasRedDistrict = true;
                    break;
            }
        }
        return (hasYellowDistrict && hasPurpleDistrict && hasGreenDistrict && hasBlueDistrict && hasRedDistrict) ? DISTRICTS_IN_ALL_COLORS_POINTS : 0;
    }
}
