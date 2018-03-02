package com.grzechwa.model;

import com.grzechwa.model.cards.character.King;
import com.grzechwa.service.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Game {
    private DeckService deckService;
    private CharacterService characterService;
    private KingshipService kingshipService;
    private AI_DecisionsService ai_decisionsService;
    private CountFinalScoreService countFinalScoreService;
    private ArrayList<Player> players;
    private Random random;
    @Setter private boolean Ended = false;

    public Game(ArrayList<Player> players){
        this.players = players;
        deckService = new DeckService();
        characterService = new CharacterService();
        kingshipService = new KingshipService(this.players);
        ai_decisionsService = new AI_DecisionsService(deckService);
        countFinalScoreService = new CountFinalScoreService(this.players);
        random = new Random();
    }

    public boolean hasEnded(){
        return Ended;
    }

    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList<Player> playersInOrderOfPlay = getPlayersStartingFromKing(players);
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
    //for now both player and AI turns are merged. It will change once basic logic will be finished.
    public void resolvingCharacterPhase(){
        ArrayList<Player> playersFromFirstToLastThisPhase = getPlayersInOrderOfCharactersAppearance(players);
        for(Player player: playersFromFirstToLastThisPhase){
            if (!player.getChoosenCharacter().isKilled()){
                if(player.getChoosenCharacter() instanceof King && !player.isKing()){
                    kingshipService.removeKing();
                    kingshipService.setKing(player);
                    setGoldOrDistrictRandomly(player);
                    ai_decisionsService.play(player,player.getChoosenCharacter());
                }else{
                    setGoldOrDistrictRandomly(player);
                    ai_decisionsService.play(player,player.getChoosenCharacter());
                }
            }else{
                System.out.println("Player "+ player.getPlayerName() + " is dead, turn moves forward");
            }
        }
        for(Player player: playersFromFirstToLastThisPhase){
            player.setChoosenCharacter(null);
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

    private void setGoldOrDistrictRandomly(Player player){
        if(random.nextInt(2) == 0){
            player.addGold(2);
        }else{
            player.addDistricts(deckService.drawDistricts(1));
        }
    }

    private ArrayList<Player> getPlayersStartingFromKing(ArrayList<Player> players){
        System.out.println("King before sorting the list "+ kingshipService.getKing().getPlayerName());
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(kingshipService.getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,kingshipService.getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        System.out.println("King after sorting list "+ orderedPlayersList.get(0).getPlayerName());
        return orderedPlayersList;
    }

    private ArrayList<Player> getPlayersInOrderOfCharactersAppearance(ArrayList<Player> players){
        System.out.println("List before sorting");
        players.forEach(player -> System.out.println("\t"+player.getPlayerName() + " " + player.getChoosenCharacter().getCardName()+ " " + player.getChoosenCharacter().getTurnOfAppearance()));
        players.sort(Comparator.comparing(player -> player.getChoosenCharacter().getTurnOfAppearance()));
        System.out.println("List after sorting. Starting from the earliest turn of appearance");
        players.forEach(player -> System.out.println("\t"+player.getPlayerName() + " " + player.getChoosenCharacter().getCardName()+ " " + player.getChoosenCharacter().getTurnOfAppearance()));
        return players;
    }
}
