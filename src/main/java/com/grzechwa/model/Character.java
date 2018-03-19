package com.grzechwa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character extends Card {
    private Player currentOwner;
    private int turnOfAppearance;
    private boolean isKilled;
    private boolean isRobbed;
    private boolean isDiscarded;
    private boolean isOnVisibleCharactersPile;
    private String imagePath;

    public Character(String cardName,
                     String cardColor,
                     boolean isVisible,
                     int turnOfAppearance,
                     boolean isKilled,
                     boolean isRobbed,
                     boolean isDiscarded,
                     boolean isOnVisibleCharactersPile,
                     String imagePath) {
                    super(cardName,cardColor,isVisible);
                    this.turnOfAppearance = turnOfAppearance;
                    this.isKilled = isKilled;
                    this.isRobbed = isRobbed;
                    this.isDiscarded = isDiscarded;
                    this.isOnVisibleCharactersPile = isOnVisibleCharactersPile;
                    this.imagePath = imagePath;
    }

    @Override
    public String toString(){
        return cardName;
    }
}
