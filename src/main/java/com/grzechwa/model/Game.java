package com.grzechwa.model;

import com.grzechwa.service.*;
import lombok.Setter;

import java.util.ArrayList;

public class Game {
    private PlayerService playerService;
    private CharacterService characterService;
    private TheftService theftService;
    private AI_DecisionsService ai_decisionsService;
    private CountFinalScoreService countFinalScoreService;
    @Setter
    private boolean Ended = false;

    public Game(PlayerService playerService,
                CharacterService characterService,
                TheftService theftService,
                AI_DecisionsService ai_decisionsService,
                CountFinalScoreService countFinalScoreService){
        this.playerService = playerService;
        this.characterService = characterService;
        this.theftService = theftService;
        this.ai_decisionsService = ai_decisionsService;
        this.countFinalScoreService = countFinalScoreService;
    }

    public boolean hasEnded(){
        return Ended;
    }

    public void choosingCharacterPhase(){
        characterService.generateCharactersToChooseFrom();
        ArrayList<Player> playersInOrderOfPlay = playerService.getPlayersStartingFromKing(playerService.getPlayers());
        for(int i = 0; i < playersInOrderOfPlay.size(); i++) {
            if (playersInOrderOfPlay.get(i).isAI() && playersInOrderOfPlay.get(i).getChoosenCharacter() == null) {
                Character randomChar = characterService.getRandomCharacterFromPossibleToPick();
                playersInOrderOfPlay.get(i).setChoosenCharacter(randomChar);
                playersInOrderOfPlay.get(i).getChoosenCharacter().setCurrentOwner(playersInOrderOfPlay.get(i));
                System.out.println(playersInOrderOfPlay.get(i).getPlayerName()+"\t choose " + playersInOrderOfPlay.get(i).getChoosenCharacter().getCardName());
            } else if (!playersInOrderOfPlay.get(i).isAI() && playersInOrderOfPlay.get(i).getChoosenCharacter() == null) {
                    playersInOrderOfPlay.get(i).setChoosenCharacter(characterService.getRandomCharacterFromPossibleToPick());
                    playersInOrderOfPlay.get(i).getChoosenCharacter().setCurrentOwner(playersInOrderOfPlay.get(i));
                System.out.println(playersInOrderOfPlay.get(i).getPlayerName()+"\t choose " + playersInOrderOfPlay.get(i).getChoosenCharacter().getCardName());
            }
        }
        System.out.println();
    }

    //for now both player and AI turns are merged. It will change once basic logic will be finished.
    public void resolvingCharacterPhase(){
        ArrayList<Player> playersInOrder = playerService.getPlayersInOrderOfCharactersAppearance(playerService.getPlayers());
        for(int i = 0 ; i < playersInOrder.size(); i++){
            if (!playersInOrder.get(i).getChoosenCharacter().isKilled()){
                if(!playersInOrder.get(i).getChoosenCharacter().isRobbed()) {
                    ai_decisionsService.play(playersInOrder.get(i));
                }else{
                    characterRobbedPath(playersInOrder.get(i));
                }
            }else{
                characterKilledPath(playersInOrder.get(i));
            }
        }
        checkWinConditions();
        playerService.resetPlayersChoosenCharacters();
        characterService.resetCharacterList();
        characterService.resetCharactersOwners();
    }

    public void checkWinConditions(){
        playerService.getPlayers().stream()
                .filter(x -> x.getFinishedDistrictsCounter() == 8)
                .limit(1)
                .forEach(x -> {
                    countFinalScoreService.countPointsForAllPlayers();
                    setEnded(true);
                });
    }

    private void characterKilledPath(Player player){
        System.out.printf("\nPlayer %s character %s has been killed, turn moves forward\n",
                player.getPlayerName(),
                player.getChoosenCharacter().getCardName());
    }

    private void characterRobbedPath(Player currentPlayer) {
        theftService.printThivery(currentPlayer);
        theftService.getGoldFromRobbed(currentPlayer);
        ai_decisionsService.play(currentPlayer);
    }
}
