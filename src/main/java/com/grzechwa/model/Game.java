package com.grzechwa.model;

import com.grzechwa.service.CharacterService;
import com.grzechwa.service.CountFinalScoreService;
import com.grzechwa.service.DeckService;
import com.grzechwa.service.KingshipService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Game {
    private DeckService deckService;
    private CharacterService characterService;
    private KingshipService kingshipService;
    private CountFinalScoreService countFinalScoreService;
    private int numberOfAIPlayers;
    @Getter private ArrayList<Player> players;
    @Setter private boolean Ended = false;
    private static int highestRankAmongCharacters;
    private static final int STARTING_GOLD_VALUE = 2;
    private static final int STARTING_DISTRICT_CARDS_VALUE = 4;

    public Game(int numberOfAllPlayers,String... playersNames){
        deckService = new DeckService();
        characterService = new CharacterService();
        players = new ArrayList<>();
        kingshipService = new KingshipService(players);
        countFinalScoreService = new CountFinalScoreService(players);
        numberOfAIPlayers = numberOfAllPlayers - playersNames.length;
        createHumanPlayers(playersNames);
        createAIPlayers();
        kingshipService.setKingRandomly();
        Game.highestRankAmongCharacters = CharacterService.getTheHighestRankAmongCharacters();
    }

    public boolean hasEnded(){
        return Ended;
    }

    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList<Player> playersInOrderOfPlay = getPlayersInOrder(players);
        Player currentPlayer;

        for(int i = 0; i < playersInOrderOfPlay.size(); i++) {
            currentPlayer = playersInOrderOfPlay.get(i);
            if (currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println(currentPlayer.getPlayerName()+" choosing character turn");
                currentPlayer.setChoosenCharacter(characterService.getRandomCharacterAndRemove());
                System.out.println("\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            } else if (!currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println(currentPlayer.getPlayerName()+" choosing character turn");
                currentPlayer.setChoosenCharacter(characterService.getRandomCharacterAndRemove());
                System.out.println("\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            }
        }
    }

    public void resolvingCharacterPhase(){
        for(int i = 1; i <= highestRankAmongCharacters; i++ ){

        }
    }

    public void checkWinConditions(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getFinishedDistrictsCounter() == 8){
                countFinalScoreService.countPointsForAllPlayers();
                setEnded(true);
                break;
            }
        }
    }

    private void createHumanPlayers(String[] playersNames){
        for(int i = 0; i < playersNames.length; i++) {
            players.add(new Player(playersNames[i],STARTING_GOLD_VALUE, new ArrayList<District> (deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),i, false));
        }
    }

    private void createAIPlayers(){
        if(numberOfAIPlayers > 0){
            for(int i = 0; i < numberOfAIPlayers; i++){
                players.add(new Player("AI "+i,STARTING_GOLD_VALUE,new ArrayList<> (deckService.drawDistricts(STARTING_DISTRICT_CARDS_VALUE)),players.size()+1, true));
            }
        }
    }

    private ArrayList<Player> getPlayersInOrder(ArrayList<Player> players){
        System.out.println("King before rearanging the list "+ kingshipService.getKing().getPlayerName());
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(kingshipService.getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,kingshipService.getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        System.out.println("King after sorting list "+ orderedPlayersList.get(0).getPlayerName());
        return orderedPlayersList;
    }
}
