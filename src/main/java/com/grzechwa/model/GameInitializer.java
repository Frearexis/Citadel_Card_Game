package com.grzechwa.model;

import com.grzechwa.service.*;
import com.grzechwa.view_controller.GameController;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Random;

public class GameInitializer {
    private PlayerService playerService;
    private CharacterService characterService;
    private DistrictService districtService;
    private DeckService deckService;
    private BuildingService buildingService;
    private KingshipService kingshipService;
    private KillingService killingService;
    private TheftService theftService;
    private DistrictDestroyingService districtDestroyingService;
    private DistrictSwappingService districtSwappingService;
    private AI_DecisionsService ai_decisionsService;
    private CountFinalScoreService countFinalScoreService;
    private GameController gameController;
    private ArrayList<Player> players;
    private Game game;
    private static final int STARTING_GOLD_VALUE = 2;
    private static final int STARTING_DISTRICT_CARDS_VALUE = 4;

    public GameInitializer(int numberOfAllPlayers, GameController gameController,String... playersNames) {
        this.gameController = gameController;
        initializePlayers(numberOfAllPlayers,playersNames);
        injectDependencies();
        kingshipService.setKingRandomly();
    }

    public Game initialize(){
        return game;
    }

    private void initializePlayers(int number, String... playersNames){
        players = new ArrayList<>();
        deckService = new DeckService();
        createHumanPlayers(playersNames);
        createAIPlayers(number-playersNames.length);
    }

    private void injectDependencies(){
        kingshipService = new KingshipService(players);
        playerService = new PlayerService(players, kingshipService, new Random());
        characterService = new CharacterService();
        districtService = new DistrictService();
        killingService = new KillingService(characterService);
        theftService = new TheftService(playerService,characterService);
        districtDestroyingService = new DistrictDestroyingService(playerService);
        districtSwappingService = new DistrictSwappingService(deckService);
        buildingService = new BuildingService(playerService,districtService);
        ai_decisionsService = new AI_DecisionsService(playerService,
                                                    districtService,
                                                    deckService,
                                                    buildingService,
                                                    kingshipService,
                                                    killingService,
                                                    theftService,
                                                    districtDestroyingService,
                                                    districtSwappingService);
        countFinalScoreService = new CountFinalScoreService(players);
        game = new Game(playerService,characterService,theftService,ai_decisionsService,countFinalScoreService,gameController);
    }

    private void createHumanPlayers(String[] playersNames){
        for(int i = 0; i < playersNames.length; i++) {
            players.add(new Player(playersNames[i],STARTING_GOLD_VALUE, FXCollections.observableList(deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),i, false));
        }
    }

    private void createAIPlayers(int numberOfAIPlayers){
        if(numberOfAIPlayers > 0){
            for(int i = 0; i < numberOfAIPlayers; i++){
                players.add(new Player("AI "+i,STARTING_GOLD_VALUE,FXCollections.observableList(deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),players.size()+1, true));
            }
        }
    }
}
