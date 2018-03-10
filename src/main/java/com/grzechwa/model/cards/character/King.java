package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class King extends Character {
    private static final String kingImagePath = "/card_images/characters/King.png";
    public King() {
        super("King",
                "yellow",
                true,
                4,
                false,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof King)) {
            return false;
        }
        King other = (King) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
