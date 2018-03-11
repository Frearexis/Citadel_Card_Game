package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Prison extends District {
    private static final String prisonImagePath = "/card_images/district/Prison.png";
    public Prison() {
        super("Prison",
                "red",
                true,
                2);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Prison)) {
            return false;
        }
        Prison other = (Prison) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
