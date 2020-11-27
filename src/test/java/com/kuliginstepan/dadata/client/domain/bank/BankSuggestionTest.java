package com.kuliginstepan.dadata.client.domain.bank;

import static org.junit.Assert.assertEquals;

import com.kuliginstepan.dadata.client.SuggestionType;
import org.junit.Test;

public class BankSuggestionTest {


    @Test
    public void getSuggestOperationPrefix() {
        assertEquals("/bank", SuggestionType.BANK.getSuggestOperationPrefix());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getFindByIdOperationPrefix() {
        SuggestionType.BANK.getFindByIdOperationPrefix();
    }
}