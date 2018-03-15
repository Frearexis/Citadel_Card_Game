package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Getter;

public final class Merchant extends Character {
    @Getter
    private static final String merchantImagePath = "/card_images/characters/Merchant.png";
    public Merchant() {
        super("Merchant",
                "green",
                true,
                6,
                false,
                false,
                false,
                false);
    }
}
