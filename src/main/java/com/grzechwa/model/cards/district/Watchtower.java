package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Watchtower extends District {
    private static final String watchtowerImagePath = "/card_images/district/Watchtower.png";
    public Watchtower() {
        super("Watchtower",
                "red",
                true,
                1);
    }
}