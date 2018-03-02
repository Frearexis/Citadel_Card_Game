package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Tavern extends District {
    private static final String tavernImagePath = "/card_images/district/Tavern.png";
    public Tavern() {
        super("Tavern",
                "green",
                true,
                1);
    }
}