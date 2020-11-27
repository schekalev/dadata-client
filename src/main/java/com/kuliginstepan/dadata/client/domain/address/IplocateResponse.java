package com.kuliginstepan.dadata.client.domain.address;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class IplocateResponse {

    @Nullable
    AddressSuggestion location;
}
