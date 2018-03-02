package com.grzechwa.model.cards.district;


import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Docks extends District {
    private static final String docksImagePath = "/card_images/district/Docks.png";
    public Docks() {
        super("Docks",
                "green",
                true,
                3);
    }
}