package com.kuliginstepan.dadata.client;

import static com.kuliginstepan.dadata.client.TestUtils.CLIENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.Address;
import com.kuliginstepan.dadata.client.domain.address.AddressSuggestion;
import com.kuliginstepan.dadata.client.domain.address.GeolocateRequest;
import java.util.List;
import org.junit.Test;

public class GeolocateRequestTest {

    @Test
    public void geolocateTest() {
        List<AddressSuggestion> suggestions = CLIENT.geolocate(new GeolocateRequest(55.601983, 37.359486, 50));

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        Address address = suggestions.get(0).getData();
        assertEquals("Москва", address.getRegion());
        assertEquals("Бианки", address.getStreet());
        assertEquals("4", address.getHouse());
        assertEquals("2", address.getBlock());
    }
}
