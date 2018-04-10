package com.grzechwa.service;

import com.grzechwa.model.Character;
import com.grzechwa.model.cards.character.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class CharacterService {

    private ArrayList<Character> characters;
    private Random random;

    public CharacterService(){
        characters = new ArrayList<>();
        generateCharacterList();
        random = new Random();
    }

    public void generateCharacterList(){
        characters.addAll(Arrays.asList(
                new Architect(),
                new Assassin(),
                new Bishop(),
                new General(),
                new King(),
                new Magician(),
                new Merchant(),
                new Thief()));
    }

    public void resetCharacterList(){
        characters.clear();
        generateCharacterList();
    }

    public void resetCharactersOwners(){
        for(Character character : characters){
            character.setCurrentOwner(null);
        }
    }

    public ArrayList<Character> getAllCharacters(){
        return characters;
    }

    public void generateCharactersToChooseFrom(){
        discardRandomCharacterFromCharacters();
        setRandomCharactersAsVisibleToAll(2);
        System.out.println("\nVisible Characters ");
            getVisibleCharacters().forEach(s->System.out.println("\t"+s.getCardName()));
        System.out.println("Characters possible to pick ");
        getCharactersPossibleToPick().forEach(s->System.out.println("\t"+s.getCardName()));
    }

    public ArrayList<Character> getVisibleCharacters(){
       return characters.stream()
               .filter(Character::isOnVisibleCharactersPile)
               .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isCharacterOnVisiblePile(Character characterToCheck){
        return getVisibleCharacters().stream()
                .anyMatch(c -> c.equals(characterToCheck));
    }

    public ArrayList<Character> getCharactersPossibleToPick(){
        ArrayList<Character> charactersPossibleToPick = new ArrayList<>();
        for(Character character : characters){
            if(!(character.isOnVisibleCharactersPile()) && !(character.isDiscarded()) && character.getCurrentOwner() == null){
                charactersPossibleToPick.add(character);
            }
        }
        return charactersPossibleToPick;
    }

    public Character getRandomCharacterFromPossibleToPick(){
        return getRandomCharacter(getCharactersPossibleToPick());
    }

    public Character getRandomCharacter(ArrayList<Character> customCharacterList){
        return customCharacterList.get(random.nextInt(customCharacterList.size()));
    }

    //This is required because of starting rules. King cannot be shown to Players.
    private Character getNotAKingCharacterFromList() {
        Character character = getRandomCharacter(characters);
        if (!character.equals(new King()) && !character.isDiscarded() && !character.isOnVisibleCharactersPile()) {
            return character;
        }
        return getNotAKingCharacterFromList();
    }

    private void discardRandomCharacterFromCharacters(){
         getRandomCharacter(characters).setDiscarded(true);
    }

    private void setRandomCharactersAsVisibleToAll(int amount){
        for (int i = 0; i < amount; i++){
            getNotAKingCharacterFromList().setOnVisibleCharactersPile(true);
        }
    }
}
