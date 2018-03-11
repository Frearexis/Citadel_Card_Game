package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Dragon_Gate extends District {
    private static final String dragonGateImagePath = "/card_images/district/Dragon_Gate.png";
    public Dragon_Gate() {
        super("Dragon Gate",
                "purple",
                true,
                8);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Dragon_Gate)) {
            return false;
        }
        Dragon_Gate other = (Dragon_Gate) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
