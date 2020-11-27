package com.kuliginstepan.dadata.client.domain.bank;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.AddressSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class BankResponse implements DadataResponse<BankSuggestion> {
    private List<BankSuggestion> suggestions;
}
