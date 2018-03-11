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

    public boolean equals(Object o) {
        if (!(o instanceof Cathedral)) {
            return false;
        }
        Cathedral other = (Cathedral) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}