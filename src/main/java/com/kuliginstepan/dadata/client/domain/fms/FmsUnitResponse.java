package com.kuliginstepan.dadata.client.domain.fms;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.fio.FioSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class FmsUnitResponse implements DadataResponse<FmsUnitSuggestion> {
    private List<FmsUnitSuggestion> suggestions;

}
