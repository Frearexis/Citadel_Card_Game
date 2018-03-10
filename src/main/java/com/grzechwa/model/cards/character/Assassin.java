package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Assassin extends Character {
    private static final String assassinImagePath = "/card_images/characters/Assassin.png";
    public Assassin() {
        super("Assassin",
                "colorless",
                true,
                1,
                false,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Assassin)) {
            return false;
        }
        Assassin other = (Assassin) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
