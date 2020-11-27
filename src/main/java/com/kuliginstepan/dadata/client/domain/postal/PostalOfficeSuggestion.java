package com.kuliginstepan.dadata.client.domain.postal;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class PostalOfficeSuggestion extends Suggestion {
    PostalOffice data;

}
