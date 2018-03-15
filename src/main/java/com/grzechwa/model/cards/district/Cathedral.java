package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Cathedral extends District {
    @Getter
    private static final String cathedralImagePath = "/card_images/district/Cathedral.png";
    public Cathedral() {
        super("Cathedral",
                "blue",
                true,
                5);
    }
}