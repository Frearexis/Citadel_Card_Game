package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Architect extends Character {
    @Getter
    private static final String architectImagePath = "/card_images/characters/Architect.png";
    public Architect() {
        super("Architect",
                "colorless",
                true,
                7,
                false,
                false,
                false,
                false);
    }
}
