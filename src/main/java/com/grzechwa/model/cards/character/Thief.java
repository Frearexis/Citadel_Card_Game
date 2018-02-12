package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Thief extends Character {
    private static final String thiefImagePath = "/card_images/characters/Thief.png";
    public Thief() {
        super("Thief",
                "colorless",
                true,
                2,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Thief)) {
            return false;
        }
        Thief other = (Thief) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
