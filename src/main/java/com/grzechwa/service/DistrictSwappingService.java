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
        System.out.println("\n------Swapping---------");
        System.out.println("\tMagician swap cards with "+targetPlayer.getPlayerName()+". Districts before "+askingPlayer.getDistrictsInHand().size());
        ArrayList<District> helperList = askingPlayer.getDistrictsInHand();
        askingPlayer.setDistrictsInHand(targetPlayer.getDistrictsInHand());
        targetPlayer.setDistrictsInHand(helperList);
        System.out.println("\tDistricts after " +askingPlayer.getDistrictsInHand().size());
        System.out.println("----------------------");
    }

    //For now it removes swapped districts from game later on they will be moved on bottom of deck
    public void swapDistrictsWithDeck(Player askingPlayer){
        System.out.println("\n------Swapping---------");
        System.out.println("\tMagician swap cards with deck. Districts before "+askingPlayer.getDistrictsInHand().size());
        int amountToSwap = askingPlayer.getDistrictsInHand().size();
        askingPlayer.setDistrictsInHand(deckService.drawDistricts(amountToSwap));
        System.out.println("\tDistricts after " +askingPlayer.getDistrictsInHand().size());
        System.out.println("----------------------");
    }
}
