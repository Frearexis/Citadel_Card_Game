package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Market extends District {
    @Getter
    private static final String marketImagePath = "/card_images/district/Market.png";
    public Market() {
        super("Market",
                "green",
                true,
                2);
    }
}
