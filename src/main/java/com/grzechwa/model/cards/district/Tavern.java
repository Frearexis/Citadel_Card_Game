package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Tavern extends District {
    @Getter
    private static final String tavernImagePath = "/card_images/district/Tavern.png";
    public Tavern() {
        super("Tavern",
                "green",
                true,
                1);
    }
}