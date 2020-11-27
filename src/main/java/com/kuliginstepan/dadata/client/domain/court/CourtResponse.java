package com.kuliginstepan.dadata.client.domain.court;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.bank.BankSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class CourtResponse implements DadataResponse<CourtSuggestion> {
    private List<CourtSuggestion> suggestions;
}
