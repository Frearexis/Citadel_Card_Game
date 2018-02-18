package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.cards.character.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CharacterService {
    private static ArrayList<Character> charactersVisibleToAll;
    private static ArrayList<Character> charactersPossibleToPick;

    private static final Architect ARCHITECT = new Architect();
    private static final Assassin ASSASSIN = new Assassin();
    private static final Bishop BISHOP = new Bishop();
    private static final General GENERAL = new General();
    private static final King KING = new King();
    private static final Magician MAGICIAN = new Magician();
    private static final Merchant MERCHANT = new Merchant();
    private static final Thief THIEF = new Thief();

    Random random;

    public CharacterService(){
        charactersVisibleToAll = new ArrayList<>();
        charactersPossibleToPick = new ArrayList<>();
        charactersPossibleToPick.addAll(Arrays.asList(ARCHITECT,ASSASSIN,BISHOP,GENERAL,KING,MAGICIAN,MERCHANT,THIEF));
        random = new Random();
    }

    public void generateCharactersToChooseFrom(){
        removeAndReturnCharacter(getRandomCharacterFromList());
        for (int i = 0; i < 2; i++){
            charactersVisibleToAll.add(removeAndReturnCharacter(getNotAKingCharacterFromList()));
        }
        System.out.println("Visible Characters ");
            charactersVisibleToAll.forEach(s->System.out.println("\t"+s.getCardName()));
        System.out.println("Characters possible to pick ");
            charactersPossibleToPick.forEach(s->System.out.println("\t"+s.getCardName()));
    }

    public ArrayList<Character> getCharactersPossibleToPick(){
        return charactersPossibleToPick;
    }

    public Character getRandomCharacterAndRemove(){
       return removeAndReturnCharacter(getRandomCharacterFromList());
    }

    public static int getTheHighestRankAmongAllCharacters(){
        int highestRank = 1;
        for(Character character : charactersPossibleToPick){
            highestRank = (character.getTurnOfAppearance() > highestRank) ? character.getTurnOfAppearance() : highestRank;
        }
        return highestRank;
    }

    private Character removeAndReturnCharacter(Character characterToRemove){
        charactersPossibleToPick.remove(characterToRemove);
        return characterToRemove;
    }

    //This is required because of starting rules. King cannot be shown to Players.
    private Character getNotAKingCharacterFromList(){
        Character character = getRandomCharacterFromList();
        if(!character.equals(KING)){
            return character;
        }
        return getNotAKingCharacterFromList();
    }

    private Character getRandomCharacterFromList(){
        Character pickedCharacter = charactersPossibleToPick.get(random.nextInt(charactersPossibleToPick.size()));
        return pickedCharacter;
    }
}
