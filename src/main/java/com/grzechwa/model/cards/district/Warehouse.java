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

    public boolean equals(Object o) {
        if (!(o instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}