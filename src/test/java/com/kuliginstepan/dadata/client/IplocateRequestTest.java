package com.kuliginstepan.dadata.client;

import static com.kuliginstepan.dadata.client.TestUtils.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.Address;
import com.kuliginstepan.dadata.client.domain.address.AddressSuggestion;
import org.junit.Test;

public class IplocateRequestTest {

    @Test
    public void iplocateTest() {
        AddressSuggestion suggestion = CLIENT.iplocate("46.226.227.20");

        assertThat(suggestion)
            .isNotNull()
            .hasFieldOrPropertyWithValue("value", "г Краснодар");
    }
    @Test
    public void iplocateNullTest() {
        AddressSuggestion suggestion = CLIENT.iplocate("46.226.227");

        assertThat(suggestion)
            .isNull();
    }
}
