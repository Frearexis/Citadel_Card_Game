package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Magician extends Character {
    @Getter
    private static final String magicianImagePath = "/card_images/characters/Magician.png";
    public Magician() {
        super("Magician",
                "colorless",
                true,
                3,
                false,
                false,
                false,
                false);
    }
}
