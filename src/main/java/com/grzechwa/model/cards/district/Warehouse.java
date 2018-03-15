package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Getter;

public final class Warehouse extends District {
    @Getter
    private static final String warehouseImagePath = "/card_images/district/Warehouse.png";
    public Warehouse() {
        super("Warehouse",
                "green",
                true,
                2);
    }
}