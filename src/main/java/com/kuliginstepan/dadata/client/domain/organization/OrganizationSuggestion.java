package com.kuliginstepan.dadata.client.domain.organization;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrganizationSuggestion extends Suggestion {
    Organization data;

}
