package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Palace extends District {
    private static final String palaceImagePath = "/card_images/district/Palace.png";
    public Palace() {
        super("Palace",
                "yellow",
                true,
                5);
    }
}