package com.kuliginstepan.dadata.client;

import com.kuliginstepan.dadata.client.domain.BasicRequest;
import com.kuliginstepan.dadata.client.exception.DadataException;
import org.junit.Test;

public class DadataClientTest {

    @Test(expected = DadataException.class)
    public void clientWithBadTokenTest() {
        DadataClient client = new DadataClientBuilder().token("123456").build();
        client.findAddressById("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void notSupportedOperationTest() {
        TestUtils.CLIENT.findById(SuggestionType.FIO, new BasicRequest("test"));
    }

}