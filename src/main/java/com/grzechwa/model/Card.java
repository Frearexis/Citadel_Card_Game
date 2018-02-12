package com.grzechwa.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
}
