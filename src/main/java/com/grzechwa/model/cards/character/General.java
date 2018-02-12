package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class General extends Character {
    private static final String generalImagePath = "/card_images/characters/General.png";
    public General() {
        super("General",
                "red",
                true,
                8,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof General)) {
            return false;
        }
        General other = (General) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
