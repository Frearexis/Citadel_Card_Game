package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Battlefield extends District {
    @Getter
    private static final String battlefieldImagePath = "/card_images/district/Battlefield.png";
    public Battlefield() {
        super("Battlefield",
                "red",
                true,
                3);
    }
}