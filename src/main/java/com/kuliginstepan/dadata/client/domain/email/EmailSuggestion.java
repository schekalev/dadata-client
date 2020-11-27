package com.kuliginstepan.dadata.client.domain.email;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class EmailSuggestion extends Suggestion {
    Email data;
}
