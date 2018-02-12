package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Dragon_Gate extends District {
    private static final String dragonGateImagePath = "/card_images/district/Dragon_Gate.png";
    public Dragon_Gate() {
        super("Dragon Gate",
                "purple",
                true,
                8);
    }
}
