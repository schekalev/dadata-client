package com.kuliginstepan.dadata.client.domain.fns;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class FnsUnitSuggestion extends Suggestion {
    FnsUnit data;

}
