package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Assassin extends Character {
    @Getter
    private static final String assassinImagePath = "/card_images/characters/Assassin.png";
    public Assassin() {
        super("Assassin",
                "colorless",
                true,
                1,
                false,
                false,
                false,
                false);
    }
}
