package com.kuliginstepan.dadata.client.domain.email;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.delivery.DeliverySuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class EmailResponse implements DadataResponse<EmailSuggestion> {
    private List<EmailSuggestion> suggestions;
}
