package com.kuliginstepan.dadata.client;

import static com.kuliginstepan.dadata.client.TestUtils.CLIENT;
import static com.kuliginstepan.dadata.client.TestUtils.getDistinctList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.*;

import java.util.List;
import org.junit.Test;

public class AddressSuggestionTest {


    @Test
    public void suggestAddressTest() {
        List<AddressSuggestion> suggestions = CLIENT
            .suggestAddress(AddressRequestBuilder.create("москва серпуховская")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(10, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("Ватутина")
            .location(FilterProperty.KLADR_ID, "65").build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.get(0).getData().getRegionKladrId().startsWith("65"));
        assertEquals(1, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest1() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("берлин")
            .location(FilterProperty.COUNTRY, "*")
            .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(10, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest2() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("башко")
            .location(FilterProperty.REGION_TYPE_FULL, "республика")
            .build());

        List<String> regionTypes = getDistinctList(it -> it.getData().getRegionType(), suggestions);

        assertThat(suggestions).isNotEmpty();
        assertThat(regionTypes)
            .isNotEmpty()
            .first().asString()
            .isEqualToIgnoringCase("респ");
    }

    @Test
    public void suggestAddressWithLocationsTest3() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("Турчанинов")
            .location(FilterProperty.REGION, "Москва")
            .restrictValue()
            .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertFalse(suggestions.get(0).getValue().contains("Москва"));
        assertEquals(1, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest4() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("Абрикосовая")
            .location(FilterProperty.REGION, "Самарская")
            .location(FilterProperty.CITY, "Тольятти")
            .build());

        List<String> regions = getDistinctList(it -> it.getData().getRegion(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, regions.size());
        assertEquals("Самарская", regions.get(0));
        assertEquals(10, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest5() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("ростов рассветная")
            .location(FilterProperty.REGION, "адыгея")
            .location(FilterProperty.REGION, "астраханская")
            .location(FilterProperty.REGION, "волгоградская")
            .location(FilterProperty.REGION, "калмыкия")
            .location(FilterProperty.REGION, "краснодарский")
            .location(FilterProperty.REGION, "ростовская")
            .build());

        List<String> regions = getDistinctList(it -> it.getData().getRegion(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, regions.size());
        assertEquals("Ростовская", regions.get(0));
        assertEquals(10, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest6() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("Ботаническая")
            .location(FilterProperty.REGION, "москва").build());

        List<String> cityKladrIds = getDistinctList(it -> it.getData().getCityKladrId(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, cityKladrIds.size());
        assertTrue(cityKladrIds.get(0).startsWith("77"));
        assertEquals(2, suggestions.size());
    }

    @Test
    public void suggestAddressWithLocationsTest7() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("московское шоссе")
            .location(FilterProperty.CITY_FIAS_ID, "110d6ad9-0b64-47cf-a2ee-7e935228799c").build());

        List<String> cityFiasIds = getDistinctList(it -> it.getData().getCityFiasId(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, cityFiasIds.size());
        assertEquals("110d6ad9-0b64-47cf-a2ee-7e935228799c", cityFiasIds.get(0));
        assertEquals(10, suggestions.size());
    }

    @Test
    public void granularAddressSuggestionTest() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("тур")
            .location(FilterProperty.REGION, "москва")
            .fromBound(Bound.STREET)
            .toBound(Bound.STREET)
            .restrictValue()
            .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
    }

    @Test
    public void granularAddressSuggestionTest1() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("12")
            .location(FilterProperty.STREET_FIAS_ID, "dd08e4e2-82ff-43b5-81f7-93f59f013974")
            .fromBound(Bound.HOUSE)
            .restrictValue()
            .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, suggestions.size());
        assertEquals("д 12", suggestions.get(0).getValue());
    }

    @Test
    public void rangingAddressSuggestionTest() {
        List<AddressSuggestion> suggestions = CLIENT.suggestAddress(AddressRequestBuilder.create("невский")
            .location(FilterProperty.KLADR_ID, "78")
            .location(FilterProperty.KLADR_ID, "47")
            .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals("Санкт-Петербург", suggestions.get(0).getData().getRegion());
        assertEquals("Ленинградская", suggestions.get(2).getData().getRegion());
    }

    @Test
    public void findAddressByIdTest() {
        AddressSuggestion suggestion = CLIENT.findAddressById("5f96fd6b-b3de-451f-b280-8fedf859e683");
        assertEquals("5f96fd6b-b3de-451f-b280-8fedf859e683", suggestion.getData().getStreetFiasId());
    }
}
