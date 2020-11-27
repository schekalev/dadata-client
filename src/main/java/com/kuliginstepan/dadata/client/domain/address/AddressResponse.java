package com.kuliginstepan.dadata.client.domain.address;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddressResponse implements DadataResponse<AddressSuggestion> {
    private List<AddressSuggestion> suggestions;
}
