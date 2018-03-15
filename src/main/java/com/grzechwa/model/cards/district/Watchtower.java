package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Watchtower extends District {
    @Getter
    private static final String watchtowerImagePath = "/card_images/district/Watchtower.png";
    public Watchtower() {
        super("Watchtower",
                "red",
                true,
                1);
    }
}