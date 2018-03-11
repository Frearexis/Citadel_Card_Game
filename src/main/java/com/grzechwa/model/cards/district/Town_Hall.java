package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Town_Hall extends District {
    private static final String townHallImagePath = "/card_images/district/Town_Hall.png";
    public Town_Hall() {
        super("Town Hall",
                "green",
                true,
                5);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Town_Hall)) {
            return false;
        }
        Town_Hall other = (Town_Hall) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
