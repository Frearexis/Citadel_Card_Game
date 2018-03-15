package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Manor extends District {
    @Getter
    private static final String manorImagePath = "/card_images/district/Manor.png";
    public Manor() {
        super("Manor",
                "yellow",
                true,
                3);
    }
}
