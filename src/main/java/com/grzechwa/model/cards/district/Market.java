package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Market extends District {
    private static final String marketImagePath = "/card_images/district/Market.png";
    public Market() {
        super("Market",
                "green",
                true,
                2);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Market)) {
            return false;
        }
        Market other = (Market) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
