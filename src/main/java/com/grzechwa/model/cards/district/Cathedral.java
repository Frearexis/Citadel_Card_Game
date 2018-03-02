package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Cathedral extends District {
    private static final String cathedralImagePath = "/card_images/district/Cathedral.png";
    public Cathedral() {
        super("Cathedral",
                "blue",
                true,
                5);
    }
}