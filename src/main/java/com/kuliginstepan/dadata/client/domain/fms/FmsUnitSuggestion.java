package com.kuliginstepan.dadata.client.domain.fms;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class FmsUnitSuggestion extends Suggestion {
    FmsUnit data;

}
