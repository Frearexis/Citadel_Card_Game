package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Battlefield extends District {
    private static final String battlefieldImagePath = "/card_images/district/Battlefield.png";
    public Battlefield() {
        super("Battlefield",
                "red",
                true,
                3);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Battlefield)) {
            return false;
        }
        Battlefield other = (Battlefield) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}