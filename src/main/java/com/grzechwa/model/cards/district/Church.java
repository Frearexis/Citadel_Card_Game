package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Church extends District {
    private static final String churchImagePath = "/card_images/district/Church.png";
    public Church() {
        super("Church",
                "blue",
                true,
                2);
    }
}