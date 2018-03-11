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

    public boolean equals(Object o) {
        if (!(o instanceof Palace)) {
            return false;
        }
        Palace other = (Palace) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}