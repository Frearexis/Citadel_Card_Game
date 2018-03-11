package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Church extends District {
    private static final String churchImagePath = "/card_images/district/Church.png";
    public Church() {
        super("Church",
                "blue",
                true,
                2);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Church)) {
            return false;
        }
        Church other = (Church) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor ) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}