package com.grzechwa.model;

import com.grzechwa.model.cards.character.King;

import com.grzechwa.service.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;

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


    //akcja jest taka że postacie characters nie mają update kto jest ich ownerem
    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList<Player> playersInOrderOfPlay = getPlayersStartingFromKing(playerService.getPlayers());
        Player currentPlayer;

        for(int i = 0; i < playersInOrderOfPlay.size(); i++) {
            currentPlayer = playersInOrderOfPlay.get(i);
            if (currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                Character randomChar = characterService.getRandomCharacterFromPossibleToPick();
                currentPlayer.setChoosenCharacter(randomChar);
                currentPlayer.getChoosenCharacter().setCurrentOwner(currentPlayer);
                System.out.println(currentPlayer.getPlayerName()+"\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            } else if (!currentPlayer.isAI() && currentPlayer.getChoosenCharacter() == null) {
                    currentPlayer.setChoosenCharacter(characterService.getRandomCharacterFromPossibleToPick());
                    currentPlayer.getChoosenCharacter().setCurrentOwner(currentPlayer);
                System.out.println(currentPlayer.getPlayerName()+"\t choose " + currentPlayer.getChoosenCharacter().getCardName());
            }
        }
    }
    //for now both player and AI turns are merged. It will change once basic logic will be finished.
    //REFACTOR!!!
    public void resolvingCharacterPhase(){
        ArrayList<Player> playersFromFirstToLastThisPhase = getPlayersInOrderOfCharactersAppearance(playerService.getPlayers());
        for(int i = 0 ; i < playersFromFirstToLastThisPhase.size(); i++){
            if (!playersFromFirstToLastThisPhase.get(i).getChoosenCharacter().isKilled()){
                if(!playersFromFirstToLastThisPhase.get(i).getChoosenCharacter().isRobbed()) {
                    if (playersFromFirstToLastThisPhase.get(i).getChoosenCharacter().equals(new King()) && !playersFromFirstToLastThisPhase.get(i).isKing()) {
                        kingshipService.removeKing();
                        kingshipService.setKing(playersFromFirstToLastThisPhase.get(i));
                        ai_decisionsService.play(playersFromFirstToLastThisPhase.get(i));
                    } else {
                        ai_decisionsService.play(playersFromFirstToLastThisPhase.get(i));
                    }
                }else{
                    playerService.getThiefPlayer(playersFromFirstToLastThisPhase).addGold(playersFromFirstToLastThisPhase.get(i).getPlayerGold());
                    playersFromFirstToLastThisPhase.get(i).removeGold(playersFromFirstToLastThisPhase.get(i).getPlayerGold());
                    if (playersFromFirstToLastThisPhase.get(i).getChoosenCharacter().equals(new King()) && !playersFromFirstToLastThisPhase.get(i).isKing()) {
                        kingshipService.removeKing();
                        kingshipService.setKing(playersFromFirstToLastThisPhase.get(i));
                        ai_decisionsService.play(playersFromFirstToLastThisPhase.get(i));
                    } else {
                        ai_decisionsService.play(playersFromFirstToLastThisPhase.get(i));
                    }
                }
            }else{
                System.out.println("Player "+ playersFromFirstToLastThisPhase.get(i).getPlayerName() + " is dead, turn moves forward");
            }
        }
        checkWinConditions();
        playerService.resetPlayersChoosenCharacters();
        characterService.resetCharacterList();
        characterService.resetCharactersOwners();
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
        ArrayList<Player> orderedPlayersList = new ArrayList<>(players.subList(kingshipService.getKingIndex(),players.size()));
        ArrayList<Player> secondPart = new ArrayList<>(players.subList(0,kingshipService.getKingIndex()));
        orderedPlayersList.addAll(secondPart);
        return orderedPlayersList;
    }

    private ArrayList<Player> getPlayersInOrderOfCharactersAppearance(ArrayList<Player> players){
        players.sort(Comparator.comparing(player -> player.getChoosenCharacter().getTurnOfAppearance()));
        return players;
    }
}
