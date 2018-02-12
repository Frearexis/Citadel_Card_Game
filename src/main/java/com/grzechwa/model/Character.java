package com.grzechwa.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class Character extends Card {
    private int turnOfAppearance;
    private boolean isKilled;
    private boolean isDiscarded;
    private boolean isOnCharacterPile;

    public Character(String cardName,
                     String cardColor,
                     boolean isVisible,
                     int turnOfAppearance,
                     boolean isKilled,
                     boolean isDiscarded,
                     boolean isOnCharacterPile) {
        super(cardName,cardColor,isVisible);
        this.turnOfAppearance = turnOfAppearance;
        this.isKilled = isKilled;
        this.isDiscarded = isDiscarded;
        this.isOnCharacterPile = isOnCharacterPile;
    }
}
