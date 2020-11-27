package com.kuliginstepan.dadata.client.domain.court;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CourtSuggestion extends Suggestion {
    Court data;
}
