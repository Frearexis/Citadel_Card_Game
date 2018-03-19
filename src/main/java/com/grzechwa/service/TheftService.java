package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.Assassin;
import com.grzechwa.model.cards.character.Thief;

import java.util.ArrayList;

//Thief ability specific class
public class TheftService {
    private PlayerService playerService;
    private CharacterService characterService;

    public TheftService(PlayerService playerService, CharacterService characterService) {
        this.playerService = playerService;
        this.characterService = characterService;
    }

    public void markRandomAsRobbed() {
        Character robbedCharacter = characterService.getRandomCharacter(getCharactersPossibleToRob());
        for (Player player : playerService.getPlayers()) {
            if (player.getChoosenCharacter().equals(robbedCharacter)) {
                player.getChoosenCharacter().setRobbed(true);
            }
        }
    }

    public void getGoldFromRobbed(Player robbedPlayer){
        Player thief = playerService.getThiefPlayer(playerService.getPlayers());
        thief.addGold(robbedPlayer.getPlayerGold());
        robbedPlayer.setPlayerGold(0);
    }

    public void printThivery(Player prey){
        Player thief = playerService.getThiefPlayer(playerService.getPlayers());
        System.out.println("\n------Thievery---------");
        System.out.printf(" %s rob character %s player %s from %d gold\n",
                thief.getPlayerName(),
                prey.getChoosenCharacter().getCardName(),
                prey.getPlayerName(),prey.getPlayerGold());
        System.out.println("\n-----------------------");
    }

    private ArrayList<Character> getCharactersPossibleToRob() {
        ArrayList<Character> charactersToRob = new ArrayList<>();
        for (Character character : characterService.getAllCharacters()) {
            if (!character.equals(new Thief()) &&
                    !character.equals(new Assassin()) &&
                    !characterService.isCharacterOnVisiblePile(character)) {
                charactersToRob.add(character);
            }
        }
        return charactersToRob;
    }
}