package com.kuliginstepan.dadata.client.domain.fns;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.fms.FmsUnitSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class FnsUnitResponse implements DadataResponse<FnsUnitSuggestion> {
    private List<FnsUnitSuggestion> suggestions;

}
