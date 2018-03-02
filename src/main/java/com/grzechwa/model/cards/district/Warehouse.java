package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Warehouse extends District {
    private static final String warehouseImagePath = "/card_images/district/Warehouse.png";
    public Warehouse() {
        super("Warehouse",
                "green",
                true,
                2);
    }
}