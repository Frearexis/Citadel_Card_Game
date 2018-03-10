package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Magician extends Character {
    private static final String magicianImagePath = "/card_images/characters/Magician.png";
    public Magician() {
        super("Magician",
                "colorless",
                true,
                3,
                false,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Magician)) {
            return false;
        }
        Magician other = (Magician) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
