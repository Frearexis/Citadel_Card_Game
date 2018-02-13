package com.grzechwa.service;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class DeckServiceTest {

    @Test
    public void drawDistrictsTest() throws Exception{
        DeckService dc = new DeckService();
        assertThat(dc.drawDistricts(1), hasSize(1));
        assertThat(dc.drawDistricts(5), hasSize(5));
    }
}
