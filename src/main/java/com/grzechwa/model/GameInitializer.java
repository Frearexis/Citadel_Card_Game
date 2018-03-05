package com.grzechwa.model;

import com.grzechwa.service.DeckService;
import com.grzechwa.service.KingshipService;
import com.grzechwa.service.PlayerService;

import java.util.ArrayList;

public class GameInitializer {
    private PlayerService playerService;
    private DeckService deckService;
    private KingshipService kingshipService;
    private ArrayList<Player> players;
    private Game game;
    private int numberOfAIPlayers;
    private static final int STARTING_GOLD_VALUE = 2;
    private static final int STARTING_DISTRICT_CARDS_VALUE = 4;

    public GameInitializer(int numberOfAllPlayers,String... playersNames) {
        players = new ArrayList<>();
        playerService = new PlayerService(players);
        deckService = new DeckService();
        kingshipService = new KingshipService(players);
        numberOfAIPlayers = numberOfAllPlayers - playersNames.length;
        createHumanPlayers(playersNames);
        createAIPlayers();
        kingshipService.setKingRandomly();
        game = new Game(playerService,deckService,kingshipService);
    }

    public Game initialize(){
        return game;
    }

    private void createHumanPlayers(String[] playersNames){
        for(int i = 0; i < playersNames.length; i++) {
            players.add(new Player(playersNames[i],STARTING_GOLD_VALUE, new ArrayList<>(deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),i, false));
        }
    }

    private void createAIPlayers(){
        if(numberOfAIPlayers > 0){
            for(int i = 0; i < numberOfAIPlayers; i++){
                players.add(new Player("AI "+i,STARTING_GOLD_VALUE,new ArrayList<> (deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),players.size()+1, true));
            }
        }
    }
}
