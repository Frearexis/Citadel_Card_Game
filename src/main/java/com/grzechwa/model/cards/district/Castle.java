package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Castle extends District {
    @Getter
    private static final String castleImagePath = "/card_images/district/Castle.png";
    public Castle() {
        super("Castle",
                "yellow",
                true,
                4);
    }
}
