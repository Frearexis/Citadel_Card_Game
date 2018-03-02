package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Temple extends District {
    private static final String templeImagePath = "/card_images/district/Temple.png";
    public Temple() {
        super("Temple",
                "blue",
                true,
                1);
    }
}