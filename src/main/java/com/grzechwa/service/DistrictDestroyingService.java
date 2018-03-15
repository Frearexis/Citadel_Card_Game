package com.grzechwa.service;

import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class DistrictDestroyingService {
    private PlayerService playerService;

    public DistrictDestroyingService(PlayerService playerService){
        this.playerService = playerService;
    }

    //for now General is looking for free destroying (Districts with cost 1).
    public void basicDestroying(Player warlordPlayer){
        ArrayList<Player> ownersOfLowCostDistricts = playerService.getPlayersWithOneCostDistrictBuilded(warlordPlayer);
        if(ownersOfLowCostDistricts.size() > 0){
            Player playerWithDistrictToDestroy = playerService.getRandomPlayerPossibleToDestroy(ownersOfLowCostDistricts);
            if(playerWithDistrictToDestroy != null){
                System.out.println("\n------District Destroying-------");
                Collections.sort(playerWithDistrictToDestroy.getFinishedDistricts());
                System.out.printf("Warlord destroys district %s player %s ",playerWithDistrictToDestroy.getFinishedDistricts().get(0).getCardName(),playerWithDistrictToDestroy.getPlayerName());
                System.out.println("-----------------------");
                playerWithDistrictToDestroy.getFinishedDistricts().remove(0); //removeFromFinished
                playerWithDistrictToDestroy.decrementFinishedDistrictsCounter();
            }
        }
    }
}
