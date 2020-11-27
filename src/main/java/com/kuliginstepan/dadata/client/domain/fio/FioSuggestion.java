package com.kuliginstepan.dadata.client.domain.fio;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class FioSuggestion extends Suggestion {
    Fio data;
}
