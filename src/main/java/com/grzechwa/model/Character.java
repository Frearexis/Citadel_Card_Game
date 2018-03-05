package com.grzechwa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
public abstract class Character extends Card {
    private Player currentOwner;
    private int turnOfAppearance;
    private boolean isKilled;
    private boolean isDiscarded;
    private boolean isOnVisibleCharactersPile;

    public Character(String cardName,
                     String cardColor,
                     boolean isVisible,
                     int turnOfAppearance,
                     boolean isKilled,
                     boolean isDiscarded,
                     boolean isOnVisibleCharactersPile) {
                    super(cardName,cardColor,isVisible);
                    this.turnOfAppearance = turnOfAppearance;
                    this.isKilled = isKilled;
                    this.isDiscarded = isDiscarded;
                    this.isOnVisibleCharactersPile = isOnVisibleCharactersPile;
    }
}
