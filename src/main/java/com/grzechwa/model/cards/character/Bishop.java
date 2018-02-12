package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Bishop extends Character {
    private static final String bishopImagePath = "/card_images/characters/Bishop.png";
    public Bishop() {
        super("Bishop",
                "blue",
                true,
                5,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Bishop)) {
            return false;
        }
        Bishop other = (Bishop) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
