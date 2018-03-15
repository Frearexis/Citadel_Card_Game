package com.grzechwa.model;

import lombok.Getter;

@Getter
public abstract class Card {
    protected String cardName;
    protected String cardColor;
    protected boolean isVisible;
    protected static final String cardBackImagePath = "/card_images/back/Card_Back.png";

    public Card(String cardName, String cardColor, boolean isVisible) {
        this.cardName = cardName;
        this.cardColor = cardColor;
        this.isVisible = isVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (!cardName.equals(card.cardName)) return false;
        return cardColor.equals(card.cardColor);
    }

    @Override
    public int hashCode() {
        int result = cardName.hashCode();
        result = 31 * result + cardColor.hashCode();
        return result;
    }
}
