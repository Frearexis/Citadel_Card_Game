package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Fortress extends District {
    private static final String fortressImagePath = "/card_images/district/Fortress.png";
    public Fortress() {
        super("Fortress",
                "red",
                true,
                5);
    }
}