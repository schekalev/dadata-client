package com.kuliginstepan.dadata.client.domain.bank;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class BankSuggestion extends Suggestion {
    Bank data;
}
