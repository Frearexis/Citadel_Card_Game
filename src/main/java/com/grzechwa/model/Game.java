package com.grzechwa.model;

import com.grzechwa.service.CharacterService;
import com.grzechwa.service.DeckService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private DeckService deckService;
    private CharacterService characterService;
    private int numberOfAIPlayers;
    @Getter private ArrayList<Player> players;
    @Setter private boolean Ended = false;

    public Game(int numberOfAllPlayers,String... playersNames){
        deckService = new DeckService();
        characterService = new CharacterService();
        players = new ArrayList<>();
        numberOfAIPlayers = numberOfAllPlayers - playersNames.length;
        createHumanPlayers(playersNames);
        createAIPlayers();
        setKingRandomly();
    }

    public boolean hasEnded(){
        return Ended;
    }

    private void createHumanPlayers(String[] playersNames){
        for(int i = 0; i < playersNames.length; i++) {
            players.add(new Player(playersNames[i], new ArrayList<District> (deckService.drawDistricts(3)),i, false));
        }
    }

    private void createAIPlayers(){
        if(numberOfAIPlayers > 0){
            for(int i = 0; i < numberOfAIPlayers; i++){
                players.add(new Player("AI "+i,new ArrayList<> (deckService.drawDistricts(3)),players.size()+1, true));
            }
        }
    }

    private void setKingRandomly(){
        removeKing();
        players.get(new Random().nextInt(players.size())).setKing(true);
    }

    private void setKing(Player player){
        player.setKing(true);
    }

    public Player getKing(){
        Player tempPlayer = null;
        for(Player player: players){
            if (player.isKing()){
                tempPlayer = player;
            }
        }
        return tempPlayer;
    }

    public int getKingIndex(){
        for(int i = 0; i < players.size(); i++){
            if (players.get(i).isKing()){
              return i;
            }
        }
        return -1;
    }

    private void removeKing(){
        for(Player player: players){
            if (player.isKing()){
                player.setKing(false);
            }
        }
    }

    private ArrayList<Player> getPlayersInOrder(ArrayList<Player> players){
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        return orderedPlayersList;
    }

    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList playersInOrderOfPlay = getPlayersInOrder(players);
        Player currentPlayer;

        for(int i = 0; i < playersInOrderOfPlay.size(); i++) {
            currentPlayer = players.get(i);
            if (currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println("Computer turn");
                currentPlayer.setChoosenCharacter(characterService.getRandomCharacterAndRemove());
                System.out.println("Computer choose " + currentPlayer.getChoosenCharacter().getCardName());
            } else if (!currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println("Player turn");
                currentPlayer.setChoosenCharacter(characterService.getRandomCharacterAndRemove());
                System.out.println("Player choose " + currentPlayer.getChoosenCharacter().getCardName());
            }
        }
    }

    public void resolvingCharacterPhase(){
        //constant to implement "the biggest rank of character"
        for(int i = 1; i <= 8; i++ ){

        }
    }

    private void checkWinConditions(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getFinishedDistrictsCounter() == 8){
                //System.out.println("Player "+ players.get(i) + "won the game with " + countPointsForAllPlayers() + " points");
            }
        }
    }

    private void countPointsForAllPlayers(){
        for(Player player : players){
            player.setFinalScore(
                    countPointsForGoldLeft(player)
                    +countPointsFromDistrictsLeftInHand(player)
                    +countPointsFromDistrictsBuilded(player)
                    +districtsInAllColorsBonus(player));
        }
    }

    private int countPointsForGoldLeft(Player player){
        return player.getPlayerGold();
    }

    private int countPointsFromDistrictsLeftInHand(Player player){
        return player.getDistrictsInHand().size();
    }

    private int countPointsFromDistrictsBuilded(Player player){
        int sum = 0;
        for(District district : player.getFinishedDistricts()){
            sum += district.getDistrictCost();
        }
        return sum;
    }

    private int districtsInAllColorsBonus(Player player){
        int score = 0;
        boolean hasGoldDistrict = false;
        boolean hasPurpleDistrict = false;
        boolean hasGreenDistrict = false;
        boolean hasBlueDistrict = false;
        boolean hasRedDistrict = false;
        for(District district : player.getFinishedDistricts()){
            switch (district.getCardColor()){
                case "gold":    hasGoldDistrict = true;
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
       if(hasGoldDistrict && hasPurpleDistrict && hasGreenDistrict && hasBlueDistrict && hasRedDistrict){
            score = 20;
       }
       return score;
    }
}
