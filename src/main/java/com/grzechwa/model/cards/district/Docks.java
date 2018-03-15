package com.grzechwa.model.cards.district;


import com.grzechwa.model.District;
import lombok.Getter;

public final class Docks extends District {
    @Getter
    private static final String docksImagePath = "/card_images/district/Docks.png";
    public Docks() {
        super("Docks",
                "green",
                true,
                3);
    }
}