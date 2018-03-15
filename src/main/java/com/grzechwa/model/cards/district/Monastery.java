package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Monastery extends District {
    @Getter
    private static final String monasteryImagePath = "/card_images/district/Monastery.png";
    public Monastery() {
        super("Monastery",
                "blue",
                true,
                3);
    }
}
