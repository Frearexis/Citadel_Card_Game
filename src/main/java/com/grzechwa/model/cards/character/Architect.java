package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Architect extends Character {
    private static final String architectImagePath = "/card_images/characters/Architect.png";
    public Architect() {
        super("Architect",
                "colorless",
                true,
                7,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Architect)) {
            return false;
        }
        Architect other = (Architect) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
