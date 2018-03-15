package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class General extends Character {
    @Getter
    private static final String generalImagePath = "/card_images/characters/General.png";
    public General() {
        super("General",
                "red",
                true,
                8,
                false,
                false,
                false,
                false);
    }
}
