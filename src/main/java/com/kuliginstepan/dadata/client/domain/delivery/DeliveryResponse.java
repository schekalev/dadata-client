package com.kuliginstepan.dadata.client.domain.delivery;

import com.kuliginstepan.dadata.client.domain.DadataResponse;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.bank.BankSuggestion;
import com.kuliginstepan.dadata.client.domain.court.CourtSuggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class DeliveryResponse implements DadataResponse<DeliverySuggestion> {
    private List<DeliverySuggestion> suggestions;

}
