package com.kuliginstepan.dadata.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnit;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitFilter;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitRequest;
import java.util.List;

import com.kuliginstepan.dadata.client.domain.fms.FmsUnitSuggestion;
import org.junit.Test;

public class FmsUnitSuggestionTest {

    @Test
    public void suggestFmsUnitTest() {
        List<FmsUnitSuggestion> suggestions = TestUtils.CLIENT.suggestFmsUnit(
            FmsUnitRequest.builder()
                .query("иванов")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        FmsUnit fmsUnit = suggestions.get(0).getData();
        assertNotNull(fmsUnit.getCode());
        assertNotNull(fmsUnit.getName());
        assertNotNull(fmsUnit.getRegionCode());
        assertNotNull(fmsUnit.getType());
    }

    @Test
    public void suggestFmsUnitWithFilterTest() {
        List<FmsUnitSuggestion> suggestions = TestUtils.CLIENT.suggestFmsUnit(
            FmsUnitRequest.builder()
                .query("иванов")
                .filter(FmsUnitFilter.builder()
                    .regionCode("23")
                    .build())
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        suggestions.forEach(suggestion -> assertEquals("23", suggestion.getData().getRegionCode()));
    }

    @Test
    public void findFmsUnitByIdTest() {
        FmsUnitSuggestion suggestion = TestUtils.CLIENT.findFmsUnitById("780-005");

        assertNotNull(suggestion);

        FmsUnit fmsUnit = suggestion.getData();
        assertNotNull(fmsUnit.getCode());
        assertNotNull(fmsUnit.getName());
        assertNotNull(fmsUnit.getRegionCode());
        assertNotNull(fmsUnit.getType());
    }


}
