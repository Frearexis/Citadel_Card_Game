package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Monastery extends District {
    private static final String monasteryImagePath = "/card_images/district/Monastery.png";
    public Monastery() {
        super("Monastery",
                "blue",
                true,
                3);
    }
}
