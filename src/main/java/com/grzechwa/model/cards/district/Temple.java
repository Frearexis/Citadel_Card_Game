package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Temple extends District {
    @Getter
    private static final String templeImagePath = "/card_images/district/Temple.png";
    public Temple() {
        super("Temple",
                "blue",
                true,
                1);
    }
}