package com.grzechwa.model.cards.district;

import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Harbor extends District {
    private static final String harborImagePath = "/card_images/district/Harbor.png";
    public Harbor() {
        super("Harbor",
                "green",
                true,
                4);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Harbor)) {
            return false;
        }
        Harbor other = (Harbor) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
