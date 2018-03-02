package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Battlefield extends District {
    private static final String battlefieldImagePath = "/card_images/district/Battlefield.png";
    public Battlefield() {
        super("Battlefield",
                "red",
                true,
                3);
    }
}