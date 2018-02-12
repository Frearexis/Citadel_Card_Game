package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Town_Hall extends District {
    private static final String townHallImagePath = "/card_images/district/Town_Hall.png";
    public Town_Hall() {
        super("Town Hall",
                "green",
                true,
                5);
    }
}
