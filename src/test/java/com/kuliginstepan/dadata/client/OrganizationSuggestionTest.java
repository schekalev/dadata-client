package com.kuliginstepan.dadata.client;

import static com.kuliginstepan.dadata.client.TestUtils.CLIENT;
import static com.kuliginstepan.dadata.client.TestUtils.getDistinctList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.kuliginstepan.dadata.client.domain.OrganizationStatus;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.organization.*;

import java.util.List;
import org.junit.Test;

public class OrganizationSuggestionTest {

    @Test
    public void suggestOrganizationByNameTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(OrganizationRequestBuilder.create("Сбербанк").build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(10, suggestions.size());
        assertEquals("ПАО СБЕРБАНК", suggestions.get(0).getValue());
    }

    @Test
    public void suggestOrganizationByInnTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(OrganizationRequestBuilder.create("2222808138").build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(1, suggestions.size());
        assertEquals("ООО \"РОМАШКА\"", suggestions.get(0).getValue());
    }

    @Test
    public void suggestOrganizationByFioTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(OrganizationRequestBuilder.create("Коварский Станислав Альбертович").build());

        assertThat(suggestions)
            .hasSize(7)
            .extracting(Suggestion::getValue)
            .contains("ИП Коварский Станислав Альбертович", "ООО \"ВЕГА\"");
    }

    @Test
    public void suggestOrganizationWithLocationTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(OrganizationRequestBuilder.create("тануки").location("77").build());

        List<String> regionKladrIds = getDistinctList(it -> it.getData().getAddress().getData().getRegionKladrId(),
            suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(3, suggestions.size());
        assertEquals(1, regionKladrIds.size());
        assertTrue(regionKladrIds.get(0).startsWith("77"));
    }

    @Test
    public void suggestOrganizationWithStatusTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(
                OrganizationRequestBuilder.create("сбербанк").status(OrganizationStatus.ACTIVE).build());

        List<OrganizationStatus> statuses = getDistinctList(it -> it.getData().getState().getStatus(), suggestions);
        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(10, suggestions.size());
        assertEquals(1, statuses.size());
        assertSame(statuses.get(0), OrganizationStatus.ACTIVE);
    }

    @Test
    public void suggestOrganizationWithTypeTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(
                OrganizationRequestBuilder.create("сбербанк").organizationType(OrganizationType.LEGAL).build());

        List<OrganizationType> types = getDistinctList(it -> it.getData().getType(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(10, suggestions.size());
        assertEquals(1, types.size());
        assertSame(types.get(0), OrganizationType.LEGAL);
    }

    @Test
    public void suggestOrganizationWithBranchTypeTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(
                OrganizationRequestBuilder.create("пао сбербанк").branchType(BranchType.MAIN).build());

        List<BranchType> types = getDistinctList(it -> it.getData().getBranchType(), suggestions);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertEquals(2, suggestions.size());
        assertEquals(1, types.size());
        assertSame(types.get(0), BranchType.MAIN);
    }

    @Test
    public void rangingSuggestOrganizationTest() {
        List<OrganizationSuggestion> suggestions = CLIENT
            .suggestOrganization(
                OrganizationRequestBuilder.create("ип муромова").locationBoost("56").build());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.get(0).getData().getAddress().getData().getKladrId().startsWith("56"));
    }

    @Test
    public void findOrganizationByInnTest() {
        OrganizationSuggestion organization = CLIENT.findOrganizationById("7725002343");

        assertEquals("7725002343", organization.getData().getInn());
    }

    @Test
    public void findOrganizationByOgrnTest() {
        OrganizationSuggestion organization = CLIENT.findOrganizationById("1027739468877");

        assertEquals("1027739468877", organization.getData().getOgrn());
    }

    @Test
    public void findOrganizationByInnAndKppTest() {
        OrganizationSuggestion suggestion = CLIENT
            .findOrganizationById(FindOrganizationByIdRequest.builder().query("7702070139").kpp("526002001").build());

        assertEquals("7702070139", suggestion.getData().getInn());
        assertEquals("526002001", suggestion.getData().getKpp());
    }
}
