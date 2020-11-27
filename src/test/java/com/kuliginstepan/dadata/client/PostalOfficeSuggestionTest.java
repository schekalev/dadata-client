package com.kuliginstepan.dadata.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.postal.PostalOffice;
import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeFilter;
import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeRequest;
import java.util.List;

import com.kuliginstepan.dadata.client.domain.postal.PostalOfficeSuggestion;
import org.junit.Test;

public class PostalOfficeSuggestionTest {

    @Test
    public void suggestPostalOfficeTest() {
        List<PostalOfficeSuggestion> suggestions = TestUtils.CLIENT.suggestPostalOffice(
            PostalOfficeRequest.builder()
                .query("32")
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        PostalOffice office = suggestions.get(0).getData();
        assertNotNull(office.getIndex());
        assertNotNull(office.getOpsname());
        assertNotNull(office.getOpstype());
    }

    @Test
    public void suggestPostalOfficeWithFilterTest() {
        List<PostalOfficeSuggestion> suggestions = TestUtils.CLIENT.suggestPostalOffice(
            PostalOfficeRequest.builder()
                .query("32")
                .filter(PostalOfficeFilter.builder()
                    .region("Самарская область")
                    .city("Самара")
                    .build())
                .build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());

        suggestions.stream()
            .map(PostalOfficeSuggestion::getData).forEach(office -> {
            assertTrue(office.getIndex().contains("32"));
            assertTrue(office.getCity().equalsIgnoreCase("Самара"));
            assertTrue(office.getRegion().equalsIgnoreCase("Самарская область"));
        });
    }

    @Test
    public void findFmsUnitByIdTest() {
        PostalOfficeSuggestion suggestion = TestUtils.CLIENT.findPostalOfficeById("157505");

        assertNotNull(suggestion);

        PostalOffice office = suggestion.getData();
        assertEquals("157505", office.getIndex());
        assertEquals("ШАРЬЯ", office.getCity());
    }


}
