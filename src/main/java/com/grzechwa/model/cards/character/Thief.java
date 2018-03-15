package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Thief extends Character {
    @Getter
    private static final String thiefImagePath = "/card_images/characters/Thief.png";
    public Thief() {
        super("Thief",
                "colorless",
                true,
                2,
                false,
                false,
                false,
                false);
    }
}
