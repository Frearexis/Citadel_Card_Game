package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Harbor extends District {
    @Getter
    private static final String harborImagePath = "/card_images/district/Harbor.png";
    public Harbor() {
        super("Harbor",
                "green",
                true,
                4);
    }
}
