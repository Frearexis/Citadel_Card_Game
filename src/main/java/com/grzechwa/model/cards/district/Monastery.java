package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Monastery extends District {
    private static final String monasteryImagePath = "/card_images/district/Monastery.png";
    public Monastery() {
        super("Monastery",
                "blue",
                true,
                3);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Monastery)) {
            return false;
        }
        Monastery other = (Monastery) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
