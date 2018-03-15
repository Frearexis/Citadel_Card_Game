package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Prison extends District {
    @Getter
    private static final String prisonImagePath = "/card_images/district/Prison.png";
    public Prison() {
        super("Prison",
                "red",
                true,
                2);
    }
}
