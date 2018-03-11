package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Castle extends District {
    private static final String castleImagePath = "/card_images/district/Castle.png";
    public Castle() {
        super("Castle",
                "yellow",
                true,
                4);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Castle)) {
            return false;
        }
        Castle other = (Castle) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
