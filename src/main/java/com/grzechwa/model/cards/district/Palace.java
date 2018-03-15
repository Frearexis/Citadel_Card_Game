package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Palace extends District {
    @Getter
    private static final String palaceImagePath = "/card_images/district/Palace.png";
    public Palace() {
        super("Palace",
                "yellow",
                true,
                5);
    }
}