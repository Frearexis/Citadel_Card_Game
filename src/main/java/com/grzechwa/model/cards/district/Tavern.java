package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Tavern extends District {
    private static final String tavernImagePath = "/card_images/district/Tavern.png";
    public Tavern() {
        super("Tavern",
                "green",
                true,
                1);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Tavern)) {
            return false;
        }
        Tavern other = (Tavern) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}