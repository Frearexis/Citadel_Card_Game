package com.grzechwa.service;

import com.grzechwa.model.District;
import com.grzechwa.model.Player;

import java.util.ArrayList;

public class DistrictSwappingService {
    private PlayerService playerService;
    private DeckService deckService;

    public DistrictSwappingService(PlayerService playerService, DeckService deckService){
        this.playerService = playerService;
        this.deckService = deckService;
    }

    public void swapDistrictsBetweenPlayers(Player askingPlayer, Player targetPlayer ){
        ArrayList<District> helperList = askingPlayer.getDistrictsInHand();
        askingPlayer.setDistrictsInHand(targetPlayer.getDistrictsInHand());
        targetPlayer.setDistrictsInHand(helperList);
    }

    public ArrayList<District> swapDistrictsWithDeck(Player askingPlayer){
        return new ArrayList<>();
    }

}
