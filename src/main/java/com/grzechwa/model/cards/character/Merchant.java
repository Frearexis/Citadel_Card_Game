package com.grzechwa.model.cards.character;

import com.grzechwa.model.Character;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public final class Merchant extends Character {
    private static final String merchantImagePath = "/card_images/characters/Merchant.png";
    public Merchant() {
        super("Merchant",
                "green",
                true,
                6,
                false,
                false,
                false);
    }
    public boolean equals(Object o) {
        if (!(o instanceof Merchant)) {
            return false;
        }
        Merchant other = (Merchant) o;
        return cardName.equals(other.cardName) && cardColor.equals(other.cardColor);
    }

    public int hashCode() {
        return cardName.hashCode();
    }
}
