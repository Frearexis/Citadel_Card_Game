package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Castle extends District {
    private static final String castleImagePath = "/card_images/district/Castle.png";
    public Castle() {
        super("Castle",
                "yellow",
                true,
                4);
    }
}
