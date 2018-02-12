package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Market extends District {
    private static final String marketImagePath = "/card_images/district/Market.png";
    public Market() {
        super("Market",
                "green",
                true,
                2);
    }
}
