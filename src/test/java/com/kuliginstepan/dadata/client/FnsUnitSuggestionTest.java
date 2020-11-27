package com.kuliginstepan.dadata.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnit;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitRequest;
import java.util.List;

import com.kuliginstepan.dadata.client.domain.fns.FnsUnitSuggestion;
import org.junit.Test;

public class FnsUnitSuggestionTest {

    @Test
    public void suggestFnsUnitTest() {
        List<FnsUnitSuggestion> suggestions = TestUtils.CLIENT.suggestFnsUnit(
            FnsUnitRequest.builder()
                .query("ленинск")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        FnsUnit fnsUnit = suggestions.get(0).getData();
        assertNotNull(fnsUnit.getCode());
        assertNotNull(fnsUnit.getName());
        assertNotNull(fnsUnit.getInn());
    }

    @Test
    public void suggestFnsUnitWithFilterTest() {
        List<FnsUnitSuggestion> suggestions = TestUtils.CLIENT.suggestFnsUnit(
            FnsUnitRequest.builder()
                .query("ленинск")
                .regionCode("58")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        suggestions.forEach(suggestion -> assertTrue(suggestion.getData().getCode().startsWith("58")));
    }

    @Test
    public void findFnsUnitByIdTest() {
        FnsUnitSuggestion suggestion = TestUtils.CLIENT.findFnsUnitById("5836");

        assertNotNull(suggestion);

        FnsUnit fnsUnit = suggestion.getData();
        assertEquals("5836", fnsUnit.getCode());
        assertEquals("5836010018", fnsUnit.getInn());
    }


}
