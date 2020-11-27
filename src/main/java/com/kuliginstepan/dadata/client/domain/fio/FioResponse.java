package com.kuliginstepan.dadata.client.domain.fio;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.email.EmailSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class FioResponse implements DadataResponse<FioSuggestion> {
    private List<FioSuggestion> suggestions;
}
