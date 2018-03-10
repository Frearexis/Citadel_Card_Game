package com.grzechwa.service;

import com.grzechwa.model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class DistrictDestroyingService {
    private PlayerService playerService;

    public DistrictDestroyingService(PlayerService playerService){
        this.playerService = playerService;
    }

    public void basicDestroying(Player warlordPlayer){
        ArrayList<Player> ownersOfLowCostDistricts = playerService.getPlayersWithOneCostDistrictBuilded(warlordPlayer);
        if(ownersOfLowCostDistricts.size() > 0){
            Player playerWithDistrictToDestroy = playerService.getRandomPlayerPossibleToDestroy(ownersOfLowCostDistricts);
            if(playerWithDistrictToDestroy != null){
                Collections.sort(playerWithDistrictToDestroy.getFinishedDistricts());
                playerWithDistrictToDestroy.getFinishedDistricts().remove(0); //removeFromFinished
                playerWithDistrictToDestroy.decrementFinishedDistrictsCounter();
            }
        }
    }
}
