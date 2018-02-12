package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Prison extends District {
    private static final String prisonImagePath = "/card_images/district/Prison.png";
    public Prison() {
        super("Prison",
                "red",
                true,
                2);
    }
}
