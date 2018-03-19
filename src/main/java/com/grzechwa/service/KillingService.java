package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.cards.character.Assassin;

//Assassin ability specific class
public class KillingService {
    private CharacterService characterService;

    public KillingService(CharacterService characterService){
        this.characterService = characterService;
    }

    public void killRandomCharacter(){
        Character character = characterService.getRandomCharacter(characterService.getAllCharacters() );
        if(character.isOnVisibleCharactersPile() || character.equals(new Assassin())){
            killRandomCharacter();
        }else{
            character.setKilled(true);
            }
        }
    }
