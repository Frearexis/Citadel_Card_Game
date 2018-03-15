package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Bishop extends Character {
    @Getter
    private static final String bishopImagePath = "/card_images/characters/Bishop.png";
    public Bishop() {
        super("Bishop",
                "blue",
                true,
                5,
                false,
                false,
                false,
                false);
    }
}
