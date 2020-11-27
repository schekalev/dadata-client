package com.kuliginstepan.dadata.client.domain.organization;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitResponse;
import com.kuliginstepan.dadata.client.domain.fns.FnsUnitSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrganizationResponse implements DadataResponse<OrganizationSuggestion> {
    private List<OrganizationSuggestion> suggestions;

}
