package com.kuliginstepan.dadata.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.court.Court;
import com.kuliginstepan.dadata.client.domain.court.CourtRequest;
import java.util.List;

import com.kuliginstepan.dadata.client.domain.court.CourtSuggestion;
import org.junit.Test;

public class CourtSuggestionTest {

    @Test
    public void suggestCourtTest() {
        List<CourtSuggestion> suggestions = TestUtils.CLIENT.suggestCourt(
            CourtRequest.builder()
                .query("ленинск")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        Court court = suggestions.get(0).getData();
        assertNotNull(court.getCode());
        assertNotNull(court.getName());
    }

    @Test
    public void suggestCourtWithFilterTest() {
        List<CourtSuggestion> suggestions = TestUtils.CLIENT.suggestCourt(
            CourtRequest.builder()
                .query("ленинск").regionCode("58")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        suggestions.forEach(suggestion -> assertTrue(suggestion.getData().getCode().startsWith("58")));
    }

    @Test
    public void findCourtByIdTest() {
        CourtSuggestion suggestion = TestUtils.CLIENT.findCourtById("58MS0001");

        assertNotNull(suggestion);

        Court court = suggestion.getData();
        assertEquals("58MS0001", court.getCode());
        assertEquals("Судебный участок № 1 Ленинского района г. Пензы", court.getName());
    }


}
