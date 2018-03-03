package com.grzechwa.service;

import com.grzechwa.model.Deck;
import com.grzechwa.model.District;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class DeckCreatorTest {
    @Test
    public void createDeckTest() throws Exception{
        DeckCreator dc = new DeckCreator();
        Deck deck = dc.createStartingDeck();
    }
}
