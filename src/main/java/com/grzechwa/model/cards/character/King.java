package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class King extends Character {
    @Getter
    private static final String kingImagePath = "/card_images/characters/King.png";
    public King() {
        super("King",
                "yellow",
                true,
                4,
                false,
                false,
                false,
                false);
    }
}
