package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Church extends District {
    @Getter
    private static final String churchImagePath = "/card_images/district/Church.png";
    public Church() {
        super("Church",
                "blue",
                true,
                2);
    }
}