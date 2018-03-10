package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.Player;
import com.grzechwa.model.cards.character.Assassin;
import com.grzechwa.model.cards.character.Thief;

import java.util.ArrayList;

public class TheftService {
    private PlayerService playerService;
    private CharacterService characterService;

    public TheftService(PlayerService playerService, CharacterService characterService) {
        this.playerService = playerService;
        this.characterService = characterService;
    }

    public void robRandomCharacter() {
        Character robbedCharacter = characterService.getRandomCharacter(getCharactersPossibleToRob());
        for (Player player : playerService.getPlayers()) {
            if (player.getChoosenCharacter().equals(robbedCharacter)) {
                player.getChoosenCharacter().setRobbed(true);
            }
        }
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

