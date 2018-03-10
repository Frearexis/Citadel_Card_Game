package com.grzechwa.model;

import com.grzechwa.model.cards.character.King;
import com.grzechwa.model.cards.character.Thief;
import com.grzechwa.service.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Game {
    private PlayerService playerService;
    private KingshipService kingshipService;
    private CharacterService characterService;
    private AI_DecisionsService ai_decisionsService;
    private CountFinalScoreService countFinalScoreService;
    @Setter private boolean Ended = false;

    public Game(PlayerService playerService, DeckService deckService, KingshipService kingshipService){
        this.playerService = playerService;
        this.kingshipService = kingshipService;
        characterService = new CharacterService();
        ai_decisionsService = new AI_DecisionsService(deckService,characterService,playerService);
        countFinalScoreService = new CountFinalScoreService(playerService.getPlayers());
    }

    public boolean hasEnded(){
        return Ended;
    }

    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList<Player> playersInOrderOfPlay = getPlayersStartingFromKing(playerService.getPlayers());
        Player currentPlayer;

        for(int i = 0; i < playersInOrderOfPlay.size(); i++) {
            currentPlayer = playersInOrderOfPlay.get(i);
            if (currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println(currentPlayer.getPlayerName()+" choosing character turn");
                    currentPlayer.setChoosenCharacter(characterService.getRandomCharacterFromPossibleToPick());
                    currentPlayer.getChoosenCharacter().setCurrentOwner(currentPlayer);
                System.out.println("\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            } else if (!currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                System.out.println(currentPlayer.getPlayerName()+" choosing character turn");
                    currentPlayer.setChoosenCharacter(characterService.getRandomCharacterFromPossibleToPick());
                    currentPlayer.getChoosenCharacter().setCurrentOwner(currentPlayer);
                System.out.println("\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            }
        }
    }
    //for now both player and AI turns are merged. It will change once basic logic will be finished.
    public void resolvingCharacterPhase(){
        ArrayList<Player> playersFromFirstToLastThisPhase = getPlayersInOrderOfCharactersAppearance(playerService.getPlayers());
        for(Player player: playersFromFirstToLastThisPhase){
            if (!player.getChoosenCharacter().isKilled()){
                if(!player.getChoosenCharacter().isRobbed()) {
                    if (player.getChoosenCharacter().equals(new King()) && !player.isKing()) {
                        kingshipService.removeKing();
                        kingshipService.setKing(player);
                        ai_decisionsService.play(player);
                    } else {
                        ai_decisionsService.play(player);
                    }
                }else{
                    playerService.getThiefPlayer(playersFromFirstToLastThisPhase).addGold(player.getPlayerGold());
                    player.removeGold(player.getPlayerGold());
                    if (player.getChoosenCharacter() instanceof King && !player.isKing()) {
                        kingshipService.removeKing();
                        kingshipService.setKing(player);
                        ai_decisionsService.play(player);
                    } else {
                        ai_decisionsService.play(player);
                    }
                }
            }else{
                System.out.println("Player "+ player.getPlayerName() + " is dead, turn moves forward");
            }
        }
        checkWinConditions();
        playerService.resetPlayersChoosenCharacters();
        characterService.resetCharacterList();
    }

    public void checkWinConditions(){
        for(int i = 0; i < playerService.getPlayers().size(); i++){
            if(playerService.getPlayers().get(i).getFinishedDistrictsCounter() == 8){
                countFinalScoreService.countPointsForAllPlayers();
                setEnded(true);
                break;
            }
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
