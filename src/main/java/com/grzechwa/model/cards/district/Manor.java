package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Manor extends District {
    private static final String manorImagePath = "/card_images/district/Manor.png";
    public Manor() {
        super("Manor",
                "yellow",
                true,
                3);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Manor)) {
            return false;
        }
        Manor other = (Manor) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
