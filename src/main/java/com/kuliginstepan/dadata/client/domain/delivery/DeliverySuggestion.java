package com.kuliginstepan.dadata.client.domain.delivery;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class DeliverySuggestion extends Suggestion {
    Delivery data;

}
