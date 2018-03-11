package com.grzechwa.model.cards.district;


import com.grzechwa.model.District;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Docks extends District {
    private static final String docksImagePath = "/card_images/district/Docks.png";
    public Docks() {
        super("Docks",
                "green",
                true,
                3);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Docks)) {
            return false;
        }
        Docks other = (Docks) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor) && districtCost == other.districtCost;
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}