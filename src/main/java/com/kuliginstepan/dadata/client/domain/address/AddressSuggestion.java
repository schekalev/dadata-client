package com.kuliginstepan.dadata.client.domain.address;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddressSuggestion extends Suggestion {
    Address data;
}
