package com.grzechwa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character extends Card {
    private Player currentOwner;
    private int turnOfAppearance;
    private String imagePath;
    private boolean isKilled;
    private boolean isRobbed;
    private boolean isDiscarded;
    private boolean isOnVisibleCharactersPile;

    public Character(String cardName,
                     String cardColor,
                     boolean isVisible,
                     int turnOfAppearance,
                     String imagePath,
                     boolean isKilled,
                     boolean isRobbed,
                     boolean isDiscarded,
                     boolean isOnVisibleCharactersPile) {
                    super(cardName,cardColor,isVisible);
                    this.turnOfAppearance = turnOfAppearance;
                    this.imagePath = imagePath;
                    this.isKilled = isKilled;
                    this.isRobbed = isRobbed;
                    this.isDiscarded = isDiscarded;
                    this.isOnVisibleCharactersPile = isOnVisibleCharactersPile;
    }

    @Override
    public String toString(){
        return cardName;
    }
}
