package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Manor extends District {
    private static final String manorImagePath = "/card_images/district/Manor.png";
    public Manor() {
        super("Manor",
                "yellow",
                true,
                3);
    }
}
