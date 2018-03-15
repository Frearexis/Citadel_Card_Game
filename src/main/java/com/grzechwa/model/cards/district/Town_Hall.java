package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Town_Hall extends District {
    @Getter
    private static final String townHallImagePath = "/card_images/district/Town_Hall.png";
    public Town_Hall() {
        super("Town Hall",
                "green",
                true,
                5);
    }
}
