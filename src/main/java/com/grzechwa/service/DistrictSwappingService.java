package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;

public class DistrictSwappingService {
    private DeckService deckService;

    public DistrictSwappingService(DeckService deckService){
        this.deckService = deckService;
    }

    public void swapDistrictsBetweenPlayers(Player askingPlayer, Player targetPlayer ){
        ArrayList<District> helperList = askingPlayer.getDistrictsInHand();
        askingPlayer.setDistrictsInHand(targetPlayer.getDistrictsInHand());
        targetPlayer.setDistrictsInHand(helperList);
    }

    //For now it removes swapped districts from game later on they will be moved on bottom of deck
    public void swapDistrictsWithDeck(Player askingPlayer){
        int amountToSwap = askingPlayer.getDistrictsInHand().size();
        askingPlayer.setDistrictsInHand(new ArrayList<>());
        deckService.drawDistricts(amountToSwap);
    }

}
