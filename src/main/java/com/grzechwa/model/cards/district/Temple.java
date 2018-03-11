package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Temple extends District {
    private static final String templeImagePath = "/card_images/district/Temple.png";
    public Temple() {
        super("Temple",
                "blue",
                true,
                1);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Temple)) {
            return false;
        }
        Temple other = (Temple) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}