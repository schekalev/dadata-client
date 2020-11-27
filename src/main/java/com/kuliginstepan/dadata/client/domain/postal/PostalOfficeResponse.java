package com.kuliginstepan.dadata.client.domain.postal;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.organization.OrganizationSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class PostalOfficeResponse implements DadataResponse<PostalOfficeSuggestion> {
    private List<PostalOfficeSuggestion> suggestions;

}
